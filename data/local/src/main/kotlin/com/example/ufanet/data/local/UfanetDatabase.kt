package com.example.ufanet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ufanet.data.local.dao.stories.StoryDao
import com.example.ufanet.data.local.model.stories.StoryEntity

@Database(
    entities = [
        StoryEntity::class,
               ],
    version = 1,
    exportSchema = true,
)
internal abstract class UfanetDatabase: RoomDatabase() {
    abstract fun storyDao(): StoryDao
}