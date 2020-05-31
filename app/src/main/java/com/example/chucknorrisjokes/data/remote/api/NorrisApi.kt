package com.example.chucknorrisjokes.data.remote.api

import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.data.remote.model.ModelSearch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NorrisApi {

    @GET("random")
    fun getRandomJoke(): Single<ModelJoke>

    @GET("random")
    fun getRandomJokeByCategory(
        @Query("category") category:String
    ): Single<ModelJoke>

    @GET("categories")
    fun getCategories():Single<MutableList<String>>

    @GET("search")
    fun searchJokes(
        @Query("query") query:String
    ): Single<ModelSearch>
}