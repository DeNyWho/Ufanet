package com.example.ufanet.data.local.dao.stories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ufanet.data.local.model.stories.StoryEntity
import com.example.ufanet.domain.model.story.Story
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(stories: List<StoryEntity>)

    @Query("""
        SELECT * FROM story 
        WHERE :query IS NULL 
           OR newsName LIKE '%' || :query || '%'
    """)
    fun getAllStories(query: String?): Flow<List<StoryEntity>>

    @Query("UPDATE story SET isFavourite = NOT isFavourite WHERE uniqueName = :uniqueName")
    suspend fun updateFavoriteStatus(uniqueName: String)
}