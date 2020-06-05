package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.utils.EspressoIdlingResource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseRepository {

    protected fun <T> composeSingle(task: () -> Single<T>): Single<T> = task()
        .doOnSubscribe { EspressoIdlingResource.increment() } // App is busy until further notice
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doFinally {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
        }

}