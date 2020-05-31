package com.example.chucknorrisjokes.domain.repository

import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.data.remote.model.ModelSearch
import io.reactivex.Single

interface JokeRepository {
    fun getRandomJoke():Single<ModelJoke>
    fun getRandomJokeByCategory(category:String):Single<ModelJoke>
    fun searchJokes(query:String):Single<ModelSearch>
}