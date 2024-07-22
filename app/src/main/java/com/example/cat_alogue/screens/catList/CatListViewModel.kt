package com.example.cat_alogue.screens.catList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cat_alogue.data.BreedRepository
import com.example.cat_alogue.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val breedRepository: BreedRepository
) : ViewModel() {
    val state = MutableStateFlow(CatListState())

    init {
        viewModelScope.launch {
            try {
                update(
                    state.value.copy(
                        breeds = breedRepository.getBreeds()
                    )
                )
                Log.d("CAT_ALOGUE", "Breeds loaded: ${state.value.breeds}")
            } catch (exception: Exception) {
                Log.d("CAT_ALOGUE", "Error calling cat api", exception)
            }
        }
    }

    private fun update(newState: CatListState) {
        state.value = newState
    }
}

data class CatListState(
    val breeds: List<Breed> = emptyList()
)
