package com.example.chucknorrisjokes.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.databinding.ActivityDetailBinding
import com.example.chucknorrisjokes.presentation.base.BaseActivity
import com.example.chucknorrisjokes.utils.ShareImage
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.lyt_content_button.view.*
import kotlinx.android.synthetic.main.lyt_joke_share.view.*
import kotlinx.android.synthetic.main.lyt_offline.view.*

class DetailActivity : BaseActivity<ActivityDetailBinding>(){
    private val TAG = "DetailActivity"

    lateinit var viewModel: DetailViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_detail
    }

    override fun onViewReady(savedInstance: Bundle?) {
        window.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation)
        initViewModel()

        val model : ModelJoke = intent.getParcelableExtra("model")
        viewModel.setData(model)

        lytButton.btnNext.setOnClickListener {
            viewModel.getRandomJoke()
        }
        lytButton.btnShare.setOnClickListener {
            ShareImage.share(content.lytShare, this)
        }
        lytOffline.btnRetry.setOnClickListener {
            viewModel.getRandomJoke()
        }

        Log.d(TAG, "onViewReady: ");
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
    }

    override fun onBackPressed() {
       finishAfterTransition()
    }
}