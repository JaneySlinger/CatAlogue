package com.example.cat_alogue.screens.catList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CatList(
    modifier: Modifier = Modifier,
    viewModel: CatListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(CatListEvent.LoadBreeds)
    }
    Column(modifier = modifier.fillMaxSize()) {
        if(state.isLoading) {
            Text("loading")
        } else {
            Text(state.breeds.toString())
        }
    }
}