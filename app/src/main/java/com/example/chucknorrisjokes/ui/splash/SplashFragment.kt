package com.example.chucknorrisjokes.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentSplashBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import javax.inject.Inject


class SplashFragment @Inject constructor(): BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun onViewReady(savedInstance: Bundle?) {
        viewModel.countSplash()
        viewModel.complete.observe(this, Observer {
            val action = SplashFragmentDirections.actionToMainFragment()
            findNavController()
                .navigate(action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElement(
                            binding.imgSplash, binding.imgSplash.transitionName
                        ).build())
        })
    }
}