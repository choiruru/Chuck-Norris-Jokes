package com.example.chucknorrisjokes.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chucknorrisjokes.data.remote.api.NorrisApi
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import com.example.chucknorrisjokes.domain.utils.SchedulerProvider
import com.example.chucknorrisjokes.presentation.base.BaseViewModel
import com.example.chucknorrisjokes.presentation.exception.NetworkState
import javax.inject.Inject
import kotlin.random.Random

class DetailViewModel @Inject constructor(
    private val jokeRepository: JokeRepository,
    private val schedulers: SchedulerProvider
):BaseViewModel(){

    private val _joke = MutableLiveData<ModelJoke>()
    val joke: LiveData<ModelJoke> get() = _joke

    fun getRandomJoke(){
        networkState(NetworkState.LOADING)
        lastDisposable = jokeRepository.getRandomJoke()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                it.color = Random.nextInt(9)
                _joke.postValue(it)

                networkState(NetworkState.LOADED)
            }, {
                handleError(it)
            })

        lastDisposable?.let { compositeDisposable.add(it) }

    }

    fun setData(data : ModelJoke){
        networkState(NetworkState.LOADED)
        _joke.postValue(data)
    }
}