package com.example.ufanet.data.local.model.stories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story")
data class StoryEntity(
    @PrimaryKey
    val uniqueName: String,
    val url: String,
    val imageLogo: String = "",
    val newsName: String = "",
    val isFavourite: Boolean = false,
)