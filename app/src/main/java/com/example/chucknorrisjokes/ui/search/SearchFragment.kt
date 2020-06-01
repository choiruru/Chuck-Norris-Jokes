package com.example.chucknorrisjokes.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.databinding.ActivitySearchBinding
import com.example.chucknorrisjokes.presentation.base.BaseActivity
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.presentation.customview.SpacesItemDecoration
import com.example.chucknorrisjokes.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_joke.*
import kotlinx.android.synthetic.main.lyt_offline.view.*

class SearchFragment : BaseFragment<ActivitySearchBinding, SearchViewModel>(), SearchJokeAdapter.OnJokeItemClickListener {
    private val TAG = "SearchActivity"

    private lateinit var adapter:SearchJokeAdapter


    override fun getViewModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewReady(savedInstance: Bundle?) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        initViewModel()
        initSearch()
        initRecyclerview()

        lytOffline.btnRetry.setOnClickListener {
            viewModel.searchJokes(search_view.query.toString())
        }
    }

    private fun initRecyclerview(){
        adapter = SearchJokeAdapter(viewModel.jokes,this)

        rvJokes.layoutManager = LinearLayoutManager(requireContext())
        rvJokes.addItemDecoration(SpacesItemDecoration(0F,0F,6F,0F))
        rvJokes.adapter = adapter
    }

    private fun initViewModel(){
        binding.setVariable(BR.viewModel, viewModel)
    }

    fun initSearch(){
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        search_view.imeOptions = search_view.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        search_view.requestFocus()
        search_back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        search_view.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchJokes(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.searchJokes(query)
                return true
            }
        })
    }

    override fun onJokeItemClick(value: ModelJoke) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),
            Pair(lytRoot, ViewCompat.getTransitionName(lytRoot))
        ).toBundle()

        val intent = Intent(requireContext(),
            DetailActivity::class.java).apply {
            putExtra("model", value)
        }
        startActivity(intent, options)
    }
}