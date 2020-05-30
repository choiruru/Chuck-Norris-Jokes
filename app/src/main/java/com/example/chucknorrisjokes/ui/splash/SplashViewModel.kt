package com.example.chucknorrisjokes.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucknorrisjokes.presentation.base.BaseViewModel
import com.example.chucknorrisjokes.presentation.base.SingleEvents
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(): BaseViewModel() {

    private val _complete = MutableLiveData<SingleEvents<Boolean>>()

    val complete : LiveData<SingleEvents<Boolean>>
        get() = _complete

    lateinit var disposable:Disposable

    fun countSplash(){
        disposable = Observable.timer(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                _complete.value = SingleEvents(true)
            }
    }

    fun dispose(){
        disposable.dispose()
    }
}