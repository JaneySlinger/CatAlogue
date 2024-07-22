package com.example.cat_alogue

import com.example.cat_alogue.data.BreedRepository
import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.screens.catList.CatListViewModel
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
class CatListViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    private val mockBreedRepository: BreedRepository = mock {
        onBlocking { getBreeds() } doReturn breeds
    }

    private lateinit var sut: CatListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = CatListViewModel(
            mockBreedRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is created - then cat breed is populated`() = runTest {
        verify(mockBreedRepository).getBreeds()

        assert(breeds == sut.state.value.breeds)
    }

    companion object {
        val breeds = listOf(
            Breed(
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
        )
    }

}