package com.example.ufanet.core.uikit.param

import com.example.ufanet.domain.model.story.Story
import java.util.UUID

object GlobalParams {
    val Story = Story(
        imageLogo = "image",
        newsName = "Мужчина украл велосипед Мужчина украл Мужчина украл велосипед",
        uniqueName = UUID.randomUUID().toString(),
        url = "url",
    )

    val StoryList = List(10) {
        Story(
            imageLogo = "image",
            newsName = "Мужчина украл велосипед Мужчина украл Мужчина украл велосипед",
            url = "url",
            uniqueName = UUID.randomUUID().toString(),
        )
    }
}