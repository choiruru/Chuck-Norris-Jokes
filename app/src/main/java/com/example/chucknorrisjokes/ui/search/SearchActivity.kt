package com.example.chucknorrisjokes.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.annotation.RequiresApi
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
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.lyt_offline.view.*

class SearchActivity : BaseActivity<ActivitySearchBinding>(), SearchJokeAdapter.OnJokeItemClickListener {
    private val TAG = "SearchActivity"

    private lateinit var viewModel:SearchViewModel
    private lateinit var adapter:SearchJokeAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewReady(savedInstance: Bundle?) {

        initViewModel()
        initSearch()
        initRecyclerview()

        lytOffline.btnRetry.setOnClickListener {
            viewModel.searchJokes(search_view.query.toString())
        }
    }

    private fun initRecyclerview(){
        adapter = SearchJokeAdapter(viewModel.jokes,this)

        rvJokes.layoutManager = LinearLayoutManager(this)
        rvJokes.adapter = adapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this,viewModelFactory).get(SearchViewModel::class.java)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initSearch(){
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        search_view.imeOptions = search_view.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        search_view.requestFocus()
        search_back.setOnClickListener {
            finishAfterTransition()
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

    }
}