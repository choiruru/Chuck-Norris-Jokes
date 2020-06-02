package com.example.chucknorrisjokes.di.module

import com.example.chucknorrisjokes.di.scope.ApplicationScope
import com.example.chucknorrisjokes.domain.utils.SchedulerProvider
import com.example.chucknorrisjokes.presentation.AppSchedulerProvider
import com.example.chucknorrisjokes.ui.main.MainActivity
import com.example.chucknorrisjokes.ui.splash.SplashActivity
import com.example.chucknorrisjokes.ui.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}