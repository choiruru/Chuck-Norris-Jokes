package com.example.chucknorrisjokes.di.module

import com.example.chucknorrisjokes.data.remote.api.NorrisApi
import com.example.chucknorrisjokes.data.repository.CategoryRepositoryImpl
import com.example.chucknorrisjokes.data.repository.JokeRepositoryImpl
import com.example.chucknorrisjokes.di.scope.ApplicationScope
import com.example.chucknorrisjokes.domain.repository.CategoryRepository
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideJokeRepository(service: NorrisApi): JokeRepository {
        return JokeRepositoryImpl(service)
    }

    @Provides
    @ApplicationScope
    fun provideCategoryRepository(service: NorrisApi): CategoryRepository {
        return CategoryRepositoryImpl(service)
    }
}