package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.remote.api.NorrisApi
import com.example.chucknorrisjokes.data.remote.base.ErrorNetworkHandler
import com.example.chucknorrisjokes.domain.repository.CategoryRepository
import com.example.chucknorrisjokes.utils.EspressoIdlingResource
import io.reactivex.Single
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val norrisApi: NorrisApi
):CategoryRepository, BaseRepository(){
    override fun getCategories(): Single<MutableList<String>> {
        return composeSingle { norrisApi.getCategories() }
            .compose(ErrorNetworkHandler())
    }

}