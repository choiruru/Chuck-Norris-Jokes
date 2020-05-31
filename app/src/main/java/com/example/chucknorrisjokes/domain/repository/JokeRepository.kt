package com.example.chucknorrisjokes.domain.repository

import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import io.reactivex.Single

interface JokeRepository {
    fun getRandomJoke():Single<ModelJoke>
    fun getRandomJokeByCategory(category:String):Single<ModelJoke>
}