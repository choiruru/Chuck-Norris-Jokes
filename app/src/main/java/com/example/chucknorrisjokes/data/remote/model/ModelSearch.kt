package com.example.chucknorrisjokes.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelSearch(
    val result: MutableList<ModelJoke>,
    val total: Int
):Parcelable