package com.example.ufanet.feature.search.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.ufanet.core.uikit.theme.Gray4
import com.example.ufanet.core.uikit.theme.Red
import com.example.ufanet.core.uikit.util.DefaultPreview
import com.example.ufanet.core.uikit.util.clickableWithoutRipple
import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.feature.search.R
import com.example.ufanet.feature.search.component.card.param.StoriesCardPreviewParam
import com.example.ufanet.feature.search.component.card.param.StoriesCardProvider

@Composable
internal fun SearchCard(
    story: Story,
    onFavouriteClick: (String) -> Unit,
    onCardClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable {
                onCardClick.invoke(story.url)
            }
            .size(158.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(story.imageLogo)
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = "Content thumbnail",
                contentScale = ContentScale.Crop,
                onError = {
                    println(it.result.throwable.message)
                },
            )

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = story.newsName,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                Icon(
                    modifier = Modifier
                        .clickableWithoutRipple {
                            onFavouriteClick.invoke(story.uniqueName)
                        }
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    painter = if(story.isFavourite)
                        painterResource(R.drawable.feature_search_ic_favourite)
                    else
                        painterResource(R.drawable.feature_search_ic_heart),
                    contentDescription = null,
                    tint = if(story.isFavourite) Red else Gray4,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSearchCard(
    @PreviewParameter(StoriesCardProvider::class) param: StoriesCardPreviewParam,
) {
    DefaultPreview(false) {
        SearchCard(
            story = param.story,
            onFavouriteClick = param.onFavouriteClick,
            onCardClick = param.onCardClick,
        )
    }
}