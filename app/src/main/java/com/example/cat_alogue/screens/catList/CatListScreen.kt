package com.example.cat_alogue.screens.catList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.ui.CatImage
import com.example.cat_alogue.ui.LoadingScreen
import com.example.cat_alogue.ui.theme.CatalogueTheme
import com.example.cat_alogue.ui.theme.PaleBlue

@Composable
fun CatListScreen(
    modifier: Modifier = Modifier,
    viewModel: CatListViewModel = viewModel(),
    onBreedSelected: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    Column(modifier = modifier.fillMaxSize()) {
        if (state.breeds.isEmpty()) {
            LoadingScreen()
        } else {
            CatList(breeds = state.breeds, onBreedSelected = onBreedSelected)
        }
    }
}

@Composable
fun CatList(
    modifier: Modifier = Modifier,
    breeds: List<Breed>,
    onBreedSelected: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PaleBlue),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Cat-Alogue",
                style = MaterialTheme.typography.headlineLarge,
            )
        }

        LazyColumn {
            items(breeds) { breed ->
                breed.name?.let {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable { breed.id?.let { id -> onBreedSelected(id) } }
                    ) {
                        CatImage(breed.referenceImageId)
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                it, style = MaterialTheme.typography.headlineMedium
                            )
                            breed.temperament?.let {
                                Text(it)
                            }
                        }
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview
@Composable
private fun CatListPreview() {
    CatalogueTheme {
        CatList(
            breeds = listOf(
                Breed(
                    name = "Abyssinian",
                    referenceImageId = "0XYvRd7oD",
                    temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                ),
                Breed(
                    name = "Aegean", referenceImageId = "0XYvRd7oD",
                    temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                ),
                Breed(
                    name = "American Bobtail long name",
                    temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                    referenceImageId = "0XYvRd7oD"
                ),
                Breed(
                    name = "American Curl",
                    temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                    referenceImageId = "0XYvRd7oD"
                ),
            ),
        ) {}
    }
}