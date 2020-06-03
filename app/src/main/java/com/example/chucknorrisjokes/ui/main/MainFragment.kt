package com.example.chucknorrisjokes.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentMainBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.ui.search.SearchFragment
import com.example.chucknorrisjokes.utils.ShareImage
import kotlinx.android.synthetic.main.fragment_main.toolbar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.lyt_content_button.view.*
import kotlinx.android.synthetic.main.lyt_joke_share.*
import kotlinx.android.synthetic.main.lyt_offline.view.*
import javax.inject.Inject

class MainFragment @Inject constructor() :BaseFragment<FragmentMainBinding,MainViewModel>(),
    MainCategoryAdapter.OnCategoryItemClickListener{

    private val TAG = "MainFragment"

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewReady(savedInstance: Bundle?) {
        sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.splash_to_main)
        initRecyclerview()

        if(!isFragmentFromPaused){
            viewModel.start()
        }
        binding.setVariable(BR.viewModel, viewModel)

        lytButton.btnShare.setOnClickListener {
            ShareImage.share(lytShare, requireContext())
        }
        lytButton.btnNext.setOnClickListener {
            viewModel.getRandomJoke()
        }
        lytOffline.btnRetry.setOnClickListener {
            viewModel.start()
        }
        imgSwipeArrow.setOnClickListener {
            if(lytButton.visibility == View.INVISIBLE){
                binding.motionBase.transitionToStart()
            }else{
                binding.motionBase.transitionToEnd()
            }
        }

        binding.toolbar.actionSearch.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSearchFragment()
            findNavController()
                .navigate(action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElement(
                            it, getString(R.string.transition_search_back)
                        ).build())
        }

        waitForTransition(binding.toolbar.actionSearch)
        waitForTransition(binding.imgLogo)
    }

    private fun initRecyclerview(){
        val adapter = MainCategoryAdapter(viewModel.category,this)

        rvCategory.layoutManager = LinearLayoutManager(requireContext())
        rvCategory.setHasFixedSize(true)
        rvCategory.adapter = adapter
    }

    override fun onCategoryItemClick(value: String) {
        viewModel.getRandomJokeByCategory(value)
        binding.motionBase.transitionToStart()
    }
}