package com.example.chucknorrisjokes.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import com.example.chucknorrisjokes.domain.utils.SchedulerProvider
import com.example.chucknorrisjokes.presentation.base.BaseViewModel
import com.example.chucknorrisjokes.presentation.exception.NetworkState
import javax.inject.Inject
import kotlin.random.Random

class MainViewModel @Inject constructor(
    private val repository: JokeRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel() {
    private val TAG = "MainViewModel"
    val joke = MutableLiveData<ModelJoke>()

    fun getRandomJoke(){
        networkState(NetworkState.LOADING)
        lastDisposable = repository.getRandomJoke()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                it.color = Random.nextInt(9)
                joke.postValue(it)
                networkState(NetworkState.LOADED)
            }, {
                handleError(it)
            })

        lastDisposable?.let { compositeDisposable.add(it) }

    }
}