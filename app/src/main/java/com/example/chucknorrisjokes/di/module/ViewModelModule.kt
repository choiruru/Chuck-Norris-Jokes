package com.example.chucknorrisjokes.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisjokes.di.module.factory.ViewModelFactory
import com.example.chucknorrisjokes.di.scope.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory


}