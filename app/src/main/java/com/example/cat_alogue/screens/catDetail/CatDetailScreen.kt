package com.example.cat_alogue.screens.catDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cat_alogue.R
import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.screens.catList.CatImage
import com.example.cat_alogue.ui.theme.Blush
import com.example.cat_alogue.ui.theme.CatalogueTheme
import com.example.cat_alogue.ui.theme.DarkGrey
import com.example.cat_alogue.ui.theme.Orange
import com.example.cat_alogue.ui.theme.PaleBlue

@Composable
fun CatDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CatDetailViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    state.breed?.let {
        CatDetailContent(breed = state.breed!!)
    } ?: CircularProgressIndicator()


}

@Composable
fun CatDetailContent(
    breed: Breed,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PaleBlue)
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CatImage(breed.referenceImageId)
            breed.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
            breed.origin?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        Column(modifier = Modifier.padding(8.dp)) {
            breed.description?.let {
                Text(it, style = MaterialTheme.typography.bodyLarge, fontSize = 18.sp)
            }

            breed.lifeSpan?.let {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Blush),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "This breed lives about $it years",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }

            }

            breed.temperament?.let {
                Text(
                    text = "Temperament",
                    modifier = Modifier.padding(vertical = 4.dp)
                        .semantics { heading() },
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(it, style = MaterialTheme.typography.bodyLarge, fontSize = 18.sp)
            }
            Text(
                text = "Friendly with:",
                modifier = Modifier.padding(vertical = 4.dp)
                    .semantics { heading() },
                style = MaterialTheme.typography.headlineSmall,
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
}

@Composable
fun RankingRow(
    category: String,
    ranking: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clearAndSetSemantics {
                    contentDescription = "$category $ranking out of 5"
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            repeat(5) { index ->
                Icon(
                    tint = if (index < ranking) Orange else DarkGrey.copy(
                        alpha = 0.38f
                    ),
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
        CatDetailContent(
            breed = Breed(
                name = "Abyssinian",
                origin = "Egypt",
                referenceImageId = "0XYvRd7oD",
                catFriendly = 2,
                childFriendly = 4,
                dogFriendly = 3,
                strangerFriendly = 5,
                lifeSpan = "14 - 15",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            ),
        )
    }
}