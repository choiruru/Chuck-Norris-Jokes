package com.example.chucknorrisjokes.di.module

import com.example.chucknorrisjokes.di.scope.ApplicationScope
import com.example.chucknorrisjokes.domain.utils.SchedulerProvider
import com.example.chucknorrisjokes.presentation.AppSchedulerProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}