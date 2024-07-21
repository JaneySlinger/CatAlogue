package com.example.cat_alogue.screens.catDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cat_alogue.data.BreedRepository
import com.example.cat_alogue.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    breedRepository: BreedRepository,
) : ViewModel() {
    private val breedId: String = checkNotNull(savedStateHandle["breedId"])
    private var breedInfo: Breed? = null

    val state = MutableStateFlow(CatDetailState(breed = breedInfo))

    init {
        viewModelScope.launch {
            breedInfo = breedRepository.getBreedById(breedId)
            update(state.value.copy(breed = breedInfo))
        }
    }

    private fun update(newState: CatDetailState) {
        state.value = newState
    }
}

data class CatDetailState(
    val breed: Breed? = null,
)
