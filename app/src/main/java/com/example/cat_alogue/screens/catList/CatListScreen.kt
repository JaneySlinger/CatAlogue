package com.example.cat_alogue.screens.catList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cat_alogue.R
import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.ui.theme.CatalogueTheme

@Composable
fun CatListScreen(
    modifier: Modifier = Modifier,
    viewModel: CatListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(CatListEvent.LoadBreeds)
    }
    Column(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            CatList(breeds = state.breeds)
        }
    }
}

@Composable
fun CatList(
    modifier: Modifier = Modifier,
    breeds: List<Breed>
) {
    LazyColumn {
        items(breeds) { breed ->
            Row {
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
                        it,
                        Modifier.padding(8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            HorizontalDivider()
        }
    }
}


@Preview
@Composable
private fun CatListPreview() {
    CatalogueTheme {
        CatList(
            breeds = listOf(
                Breed(name = "Abyssinian", referenceImageId = "0XYvRd7oD"),
                Breed(name = "Aegean", referenceImageId = "0XYvRd7oD"),
                Breed(name = "American Bobtail", referenceImageId = "0XYvRd7oD"),
                Breed(name = "American Curl", referenceImageId = "0XYvRd7oD"),
            )
        )
    }
}