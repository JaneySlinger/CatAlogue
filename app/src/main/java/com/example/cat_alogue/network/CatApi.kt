package com.example.cat_alogue.network

import com.example.cat_alogue.model.Breed
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.thecatapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface CatApiService {
    @GET("breeds")
    suspend fun getBreeds(): List<Breed>
}

object CatApi {
    val retrofitService : CatApiService by lazy { retrofit.create(CatApiService::class.java) }
}