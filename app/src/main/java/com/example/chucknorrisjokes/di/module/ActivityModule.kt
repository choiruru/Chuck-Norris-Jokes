package com.example.chucknorrisjokes.di.module

import com.example.chucknorrisjokes.ui.main.MainActivity
import com.example.chucknorrisjokes.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [NavHostModule::class])
    abstract fun mainActivityInjector(): MainActivity

}