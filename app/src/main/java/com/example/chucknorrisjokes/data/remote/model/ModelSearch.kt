package com.example.chucknorrisjokes.data.remote.model

data class ModelSearch(
    val result: MutableList<ModelJoke>,
    val total: Int
)