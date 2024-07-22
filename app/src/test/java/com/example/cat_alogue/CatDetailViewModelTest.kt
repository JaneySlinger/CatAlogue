package com.example.cat_alogue

import androidx.lifecycle.SavedStateHandle
import com.example.cat_alogue.data.BreedRepository
import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.screens.catDetail.CatDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class CatDetailViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    private val mockSavedStateHandle: SavedStateHandle = mock {
        on { get<String>("breedId") } doReturn "beng"
    }
    private val mockBreedRepository: BreedRepository = mock {
        onBlocking { getBreedById("beng") } doReturn breed
    }

    private lateinit var sut: CatDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = CatDetailViewModel(
            mockSavedStateHandle,
            mockBreedRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is created - then cat breed is populated`() = runTest {
        verify(mockSavedStateHandle).get<String>("breedId")
        verify(mockBreedRepository).getBreedById("beng")

        assert(breed == sut.state.value.breed)
    }

    companion object {
        val breed = Breed(
            id = "beng",
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
        )
    }

}