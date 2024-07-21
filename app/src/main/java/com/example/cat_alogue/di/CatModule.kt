package com.example.cat_alogue.di

import com.example.cat_alogue.data.BreedRepository
import com.example.cat_alogue.data.BreedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatModule {
    @Singleton
    @Provides
    fun provideRepository(): BreedRepository = BreedRepositoryImpl()
}