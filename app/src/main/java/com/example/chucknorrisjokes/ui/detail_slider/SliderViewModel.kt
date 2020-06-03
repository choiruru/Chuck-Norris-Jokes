package com.example.chucknorrisjokes.ui.detail_slider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.presentation.base.BaseViewModel
import javax.inject.Inject

class SliderViewModel @Inject constructor(): BaseViewModel() {

    private var _models = MutableLiveData<List<ModelJoke>>()
    val models : LiveData<List<ModelJoke>> get() = _models

    fun setData(models : MutableList<ModelJoke>){
        _models.postValue(models)
    }
}