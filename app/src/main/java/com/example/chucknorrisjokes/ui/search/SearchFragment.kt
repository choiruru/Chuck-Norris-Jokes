package com.example.chucknorrisjokes.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.databinding.FragmentSearchBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.presentation.customview.SpacesItemDecoration
import com.example.chucknorrisjokes.utils.hideKeyboard
import com.example.chucknorrisjokes.utils.showSoftKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item_joke.*
import kotlinx.android.synthetic.main.lyt_offline.view.*
import javax.inject.Inject

class SearchFragment @Inject constructor(): BaseFragment<FragmentSearchBinding, SearchViewModel>(), SearchJokeAdapter.OnJokeItemClickListener {
    private val TAG = "SearchFragment"

    private lateinit var adapter:SearchJokeAdapter

    override fun getViewModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun onViewReady(savedInstance: Bundle?) {
        Log.d(TAG, "onViewReady: called");
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        initViewModel()
        initSearch()
        initRecyclerview()

        lytOffline.btnRetry.setOnClickListener {
            viewModel.searchJokes(binding.searchView.query.toString())
        }
    }

    private fun initRecyclerview(){
        adapter = SearchJokeAdapter(viewModel.jokes,this)

        binding.rvJokes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvJokes.addItemDecoration(SpacesItemDecoration(0F,0F,6F,0F))
        binding.rvJokes.adapter = adapter
        postponeEnterTransition()
        binding.rvJokes.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initViewModel(){
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun initSearch(){
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        binding.searchView.imeOptions = binding.searchView.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        binding.searchView.requestFocus()
        showSoftKeyboard(binding.searchView)
        binding.searchBack.setOnClickListener {
            hideKeyboard()
            requireActivity().onBackPressed()
        }
        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d(TAG, "onQueryTextSubmit: called");
                viewModel.searchJokes(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                Log.d(TAG, "onQueryTextChange: called");
                if(!isFragmentFromPaused){
                    viewModel.searchJokes(query)
                }
                return true
            }
        })
    }

    override fun onJokeItemClick(value: ModelJoke) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(value)

        findNavController()
            .navigate(action,
                FragmentNavigator.Extras.Builder()
                    .addSharedElement(
                        lytRoot, ViewCompat.getTransitionName(lytRoot).toString()
//                        mapOf(posterView to posterView.transitionName,
//                            releaseDateView to releaseDateView.transitionName,
//                            ratingView to ratingView.transitionName)
                    ).build()
            )
    }
}