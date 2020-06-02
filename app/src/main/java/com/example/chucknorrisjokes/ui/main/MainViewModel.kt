package com.example.chucknorrisjokes.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.domain.repository.CategoryRepository
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import com.example.chucknorrisjokes.domain.utils.SchedulerProvider
import com.example.chucknorrisjokes.presentation.base.BaseViewModel
import com.example.chucknorrisjokes.presentation.exception.NetworkState
import javax.inject.Inject
import kotlin.random.Random

class MainViewModel @Inject constructor(
    private val jokeRepository: JokeRepository,
    private val categoryRepository: CategoryRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel() {
    private val TAG = "MainViewModel"

    private val _joke = MutableLiveData<ModelJoke>()
    val joke: LiveData<ModelJoke> get() = _joke

    private val _categories = MutableLiveData<MutableList<String>>()
    val category: LiveData<MutableList<String>> get() = _categories

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    fun getRandomJoke(){
        _title.postValue("RANDOM")
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

    fun getRandomJokeByCategory(category:String){
        if(category.equals("random", true)){
            getRandomJoke()
        }else{
            _title.postValue(category.toUpperCase())
            networkState(NetworkState.LOADING)
            lastDisposable = jokeRepository.getRandomJokeByCategory(category)
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
    }

    fun start(){
        if(category.value==null){
            networkState(NetworkState.LOADING)
            lastDisposable = categoryRepository.getCategories()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    it.add(0,"random")
                    _categories.postValue(it)

                    if(joke.value == null){
                        getRandomJoke()
                    }else{
                        networkState(NetworkState.LOADED)
                    }
                },{
                    handleError(it)
                })
        }else{
            getRandomJoke()
        }
    }
}