package com.example.chucknorrisjokes.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import com.example.chucknorrisjokes.domain.utils.SchedulerProvider
import com.example.chucknorrisjokes.presentation.base.BaseViewModel
import com.example.chucknorrisjokes.presentation.exception.DataStatus
import com.example.chucknorrisjokes.presentation.exception.NetworkState
import javax.inject.Inject
import kotlin.random.Random

class SearchViewModel @Inject constructor(
    private val jokeRepository: JokeRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel() {

    private val _jokes = MutableLiveData<MutableList<ModelJoke>>()
    val jokes: LiveData<MutableList<ModelJoke>> get() = _jokes

    private val _dataStatus = MutableLiveData<DataStatus>()
    val dataStatus: LiveData<DataStatus>
        get() = _dataStatus

    fun searchJokes(query:String){
        if(query.length>2){
            networkState(NetworkState.LOADING)
            disposeLast()
            lastDisposable = jokeRepository.searchJokes(query)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    if(it.result.size>0){
                        for (item in it.result){
                            item.color = Random.nextInt(9)
                        }
                        _jokes.postValue(it.result)
                        _dataStatus.postValue(DataStatus.NOT_EMPTY)
                    }else{
                        _dataStatus.postValue(DataStatus.EMPTY)
                    }

                    networkState(NetworkState.LOADED)
                }, {
                    handleError(it)
                })

            lastDisposable?.let { compositeDisposable.add(it) }
        }else{
            networkState(NetworkState.LOADING)
        }
    }
}