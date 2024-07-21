package com.example.cat_alogue.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cat_alogue.R

@Composable
fun CatImage(
    referenceImageId: String?, modifier: Modifier = Modifier
) {
    AsyncImage(
        contentScale = ContentScale.Crop,
        model = "https://cdn2.thecatapi.com/images/${referenceImageId}.jpg",
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
            .size(128.dp)
            .clip(RoundedCornerShape(8.dp)),
        placeholder = painterResource(R.drawable.outline_pets_24),
        error = painterResource(R.drawable.baseline_error_outline_24),
    )
}