package com.example.cat_alogue.data

import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.network.CatApi
import javax.inject.Inject


interface BreedRepository {
    suspend fun getBreeds() : List<Breed>
    suspend fun getBreedById(breedId: String) : Breed?
    fun clearCache()
}

class BreedRepositoryImpl @Inject constructor(): BreedRepository {
    private var breeds: List<Breed> = listOf()

    override suspend fun getBreeds(): List<Breed> {
        if(breeds.isEmpty()) {
            breeds = CatApi.retrofitService.getBreeds()
        }
        return breeds
    }

    override suspend fun getBreedById(breedId: String): Breed? = breeds.find { it.id == breedId }

    override fun clearCache() {
        breeds = listOf()
    }

}