package com.example.chucknorrisjokes.domain.repository

import io.reactivex.Single

interface CategoryRepository {
    fun getCategories(): Single<MutableList<String>>
}