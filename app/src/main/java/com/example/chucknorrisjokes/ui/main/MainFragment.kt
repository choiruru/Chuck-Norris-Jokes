package com.example.chucknorrisjokes.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentMainBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.ui.search.SearchActivity
import com.example.chucknorrisjokes.utils.ShareImage
import kotlinx.android.synthetic.main.fragment_main.toolbar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.lyt_content_button.view.*
import kotlinx.android.synthetic.main.lyt_joke_share.*
import kotlinx.android.synthetic.main.lyt_offline.view.*
import javax.inject.Inject

class MainFragment @Inject constructor() :BaseFragment<FragmentMainBinding,MainViewModel>(),
    MainCategoryAdapter.OnCategoryItemClickListener{

    private val TAG = "MainFragment"

    private var category:String = ""

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewReady(savedInstance: Bundle?) {
        initRecyclerview()

        viewModel.start()
        binding.setVariable(BR.data, viewModel)

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

        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).title = "RANDOM"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search->{
                val searchMenuView: View = toolbar.findViewById(R.id.action_search)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),
                    Pair<View, String>(searchMenuView, getString(R.string.transition_search_back))
                ).toBundle()

                val intent = Intent(requireContext(),
                    SearchActivity::class.java).apply {
                    action = Intent.ACTION_SEARCH
                }
                startActivity(intent, options)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerview(){
        val adapter = MainCategoryAdapter(viewModel.category,this)

        rvCategory.layoutManager = LinearLayoutManager(requireContext())
        rvCategory.setHasFixedSize(true)
        rvCategory.adapter = adapter
    }

    override fun onCategoryItemClick(value: String) {
        (activity as MainActivity).title = value.toUpperCase()
        if(value.equals("random", true)){
            viewModel.getRandomJoke()
        }else{
            viewModel.getRandomJokeByCategory(value)
        }

        binding.motionBase.transitionToStart()
    }
}