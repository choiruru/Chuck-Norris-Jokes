package com.example.chucknorrisjokes.ui.main

import android.os.Bundle
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.ActivityMainBinding
import com.example.chucknorrisjokes.presentation.base.BaseActivity
import com.example.chucknorrisjokes.utils.addFragmentToActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}
