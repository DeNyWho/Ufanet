package com.example.ufanet.domain.model.story

data class Story(
    val imageLogo: String = "",
    val newsName: String = "",
    val url: String = "",
    val isFavourite: Boolean = false,
)