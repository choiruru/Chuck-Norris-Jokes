package com.example.chucknorrisjokes.di.component

import android.app.Application
import com.example.chucknorrisjokes.NorrisApp
import com.example.chucknorrisjokes.di.module.*
import com.example.chucknorrisjokes.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@ApplicationScope
@Component(modules = [
    ActivityModule::class,
    FragmentModule::class,
    AppModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication?> {

    fun inject(application: NorrisApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}