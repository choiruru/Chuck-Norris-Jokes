package com.example.chucknorrisjokes.di.module

import com.example.chucknorrisjokes.ui.main.MainActivity
import com.example.chucknorrisjokes.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity():SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity():MainActivity
}