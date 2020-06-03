package com.example.chucknorrisjokes.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.navigation.fragment.navArgs
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentDetailBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.utils.ShareImage
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.lyt_content_button.view.*
import kotlinx.android.synthetic.main.lyt_joke_share.view.*
import kotlinx.android.synthetic.main.lyt_offline.view.*
import javax.inject.Inject

class DetailFragment @Inject constructor() : BaseFragment<FragmentDetailBinding, DetailViewModel>(){
    private val TAG = "DetailActivity"

    private val args : DetailFragmentArgs by navArgs()

    override fun getViewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun onViewReady(savedInstance: Bundle?) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        initViewModel()

        lytButton.btnNext.setOnClickListener {
            viewModel.getRandomJoke()
        }
        lytButton.btnShare.setOnClickListener {
            ShareImage.share(content.lytShare, requireContext())
        }
        lytOffline.btnRetry.setOnClickListener {
            viewModel.getRandomJoke()
        }

        Log.d(TAG, "onViewReady: ");
        waitForTransition(binding.content.lytShare)
        waitForTransition(binding.content.textViewShare)
    }

    private fun initViewModel(){
        binding.viewModel = viewModel
        viewModel.setData(args.model)
    }
}