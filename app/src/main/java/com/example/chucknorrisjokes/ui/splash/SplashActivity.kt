package com.example.chucknorrisjokes.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.ActivitySplahBinding
import com.example.chucknorrisjokes.presentation.base.BaseActivity
import com.example.chucknorrisjokes.ui.main.MainActivity


class SplashActivity : BaseActivity<ActivitySplahBinding>() {

    private lateinit var viewModel:SplashViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_splah
    }

    override fun onViewReady(savedInstance: Bundle?) {
        viewModel = ViewModelProvider(this,viewModelFactory).get(SplashViewModel::class.java)
        viewModel.countSplash()
        viewModel.complete.observe(this, Observer {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onDestroy() {
        viewModel.dispose()
        super.onDestroy()
    }
}