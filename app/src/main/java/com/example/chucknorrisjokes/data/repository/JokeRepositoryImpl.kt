package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.remote.api.NorrisApi
import com.example.chucknorrisjokes.data.remote.base.ErrorNetworkHandler
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.data.remote.model.ModelSearch
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import com.example.chucknorrisjokes.utils.EspressoIdlingResource
import io.reactivex.Single
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val service: NorrisApi
) : JokeRepository {

    override fun getRandomJoke(): Single<ModelJoke> {
        return service.getRandomJoke()
            .doOnSubscribe { EspressoIdlingResource.increment()  }
            .doFinally {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                }
            }
            .compose(ErrorNetworkHandler())
    }

    override fun getRandomJokeByCategory(category:String): Single<ModelJoke> {
        return service.getRandomJokeByCategory(category)
            .doOnSubscribe { EspressoIdlingResource.increment()  }
            .doFinally {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                }
            }
            .compose(ErrorNetworkHandler())
    }

    override fun searchJokes(query: String): Single<ModelSearch> {
        return service.searchJokes(query)
            .doOnSubscribe { EspressoIdlingResource.increment()  }
            .doFinally {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                }
            }
            .compose(ErrorNetworkHandler())
    }
}
