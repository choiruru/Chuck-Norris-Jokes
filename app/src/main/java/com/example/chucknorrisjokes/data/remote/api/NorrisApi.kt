package com.example.chucknorrisjokes.data.remote.api

import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import io.reactivex.Single
import retrofit2.http.GET

interface NorrisApi {

    @GET("random")
    fun getRandomJoke(): Single<ModelJoke>
}