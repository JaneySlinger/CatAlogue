package com.example.cat_alogue.screens.catDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cat_alogue.R
import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.ui.theme.CatalogueTheme

@Composable
fun CatDetailScreen(
    modifier: Modifier = Modifier,
    breed: Breed,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // todo Janey the cat image shared?
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = "https://cdn2.thecatapi.com/images/${breed.referenceImageId}.jpg",
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(128.dp),
                placeholder = painterResource(R.drawable.outline_pets_24),
                error = painterResource(R.drawable.baseline_error_outline_24),
            )
            breed.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        }
        breed.description?.let {Text(it, style = MaterialTheme.typography.bodyLarge)}

        breed.temperament?.let {
            Text(
                text = "Temperament",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(it, style = MaterialTheme.typography.bodyLarge)
        }
        Text(
            text = "Friendly with:",
            style = MaterialTheme.typography.titleLarge,
        )
        breed.catFriendly?.let {
            RankingRow(
                category = "Cats",
                ranking = it,
            )
        }
        breed.dogFriendly?.let {
            RankingRow(
                category = "Dogs",
                ranking = it,
            )
        }
        breed.strangerFriendly?.let {
            RankingRow(
                category = "Strangers",
                ranking = it,
            )
        }
        breed.childFriendly?.let {
            RankingRow(
                category = "Children",
                ranking = it,
            )
        }
    }

}

@Composable
fun RankingRow(
    category: String,
    ranking: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = category, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
            repeat(5) { index ->
                Icon(
                    // Todo janey change the color
                    tint = if(index < ranking) Color.Green else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    painter = painterResource(R.drawable.outline_pets_24),
                    contentDescription = null
                )
            }
        }
        HorizontalDivider()
    }
}


@Preview
@Composable
private fun CatDetailScreenPreview() {
    CatalogueTheme {
        CatDetailScreen(
            breed = Breed(
                name = "Abyssinian",
                referenceImageId = "0XYvRd7oD",
                catFriendly = 2,
                childFriendly = 4,
                dogFriendly = 3,
                strangerFriendly = 5,
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            ),
        )
    }
}