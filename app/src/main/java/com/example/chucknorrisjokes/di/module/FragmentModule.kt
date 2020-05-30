package com.example.chucknorrisjokes.di.module

import com.example.chucknorrisjokes.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesMainFragment() : MainFragment

}