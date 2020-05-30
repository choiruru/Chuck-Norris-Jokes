package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.remote.api.NorrisApi
import com.example.chucknorrisjokes.data.remote.base.ErrorNetworkHandler
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import io.reactivex.Single
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val service: NorrisApi
) : JokeRepository {

    override fun getRandomJoke(): Single<ModelJoke> {
        return service.getRandomJoke()
            .compose(ErrorNetworkHandler())
    }
}
