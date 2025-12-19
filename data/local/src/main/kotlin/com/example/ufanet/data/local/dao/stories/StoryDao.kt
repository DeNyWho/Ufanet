package com.example.ufanet.data.local.dao.stories

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.ufanet.data.local.model.stories.StoryEntity
import com.example.ufanet.domain.model.story.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(stories: List<StoryEntity>)

    @Query("SELECT * FROM story")
    fun getAllStories(): Flow<List<StoryEntity>>

    @Transaction
    suspend fun insertAllWithPreserveFavourite(stories: List<StoryEntity>) {
        val favoriteUniqueNames = getAllStories()
            .first()
            .filter { it.isFavourite }
            .map { it.uniqueName }
            .toSet()

        val entitiesToInsert = stories.map { newEntity ->
            newEntity.copy(
                isFavourite = favoriteUniqueNames.contains(newEntity.uniqueName)
            )
        }

        insertAll(entitiesToInsert)
    }

    @Query("""
        SELECT * FROM story 
        WHERE :query IS NULL 
           OR newsName LIKE '%' || :query || '%'
    """)
    fun pagingSource(query: String?): PagingSource<Int, StoryEntity>

    @Query("UPDATE story SET isFavourite = NOT isFavourite WHERE uniqueName = :uniqueName")
    suspend fun updateFavoriteStatus(uniqueName: String)
}