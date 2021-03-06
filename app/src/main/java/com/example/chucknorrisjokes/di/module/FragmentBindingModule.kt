package com.example.chucknorrisjokes.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.chucknorrisjokes.di.module.factory.InjectingFragmentFactory
import com.example.chucknorrisjokes.di.scope.FragmentKey
import com.example.chucknorrisjokes.ui.detail.DetailFragment
import com.example.chucknorrisjokes.ui.detail_slider.DetailSliderFragment
import com.example.chucknorrisjokes.ui.main.MainFragment
import com.example.chucknorrisjokes.ui.search.SearchFragment
import com.example.chucknorrisjokes.ui.splash.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(SplashFragment::class)
    abstract fun bindSplashFragment(splashFragment: SplashFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    abstract fun bindMainFragment(mainFragment: MainFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    abstract fun bindSearchFragment(searchFragment: SearchFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    abstract fun bindDetailFragment(detailFragment: DetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailSliderFragment::class)
    abstract fun bindDetailSliderFragment(detailSliderFragment: DetailSliderFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}