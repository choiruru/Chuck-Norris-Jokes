package com.example.chucknorrisjokes.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisjokes.di.module.factory.ViewModelFactory
import com.example.chucknorrisjokes.di.scope.ApplicationScope
import com.example.chucknorrisjokes.di.scope.ViewModelKey
import com.example.chucknorrisjokes.ui.detail.DetailViewModel
import com.example.chucknorrisjokes.ui.main.MainViewModel
import com.example.chucknorrisjokes.ui.search.SearchViewModel
import com.example.chucknorrisjokes.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun providesSplashViewModel(viewModel: SplashViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun providesSearchViewModel(viewModel: SearchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun providesDetailViewModel(viewModel: DetailViewModel) : ViewModel

}