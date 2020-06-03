package com.example.chucknorrisjokes.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.data.remote.model.ModelSearch
import com.example.chucknorrisjokes.databinding.FragmentSearchBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.presentation.customview.SpacesItemDecoration
import com.example.chucknorrisjokes.utils.hideKeyboard
import com.example.chucknorrisjokes.utils.showSoftKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.lyt_offline.view.*
import javax.inject.Inject


class SearchFragment @Inject constructor() : BaseFragment<FragmentSearchBinding, SearchViewModel>(),
    SearchJokeAdapter.OnJokeItemClickListener {
    private val TAG = "SearchFragment"

    private lateinit var adapter: SearchJokeAdapter

    override fun getViewModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun onViewReady(savedInstance: Bundle?) {
        sharedElementReturnTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        initViewModel()
        initSearch()
        initRecyclerview()

        lytOffline.btnRetry.setOnClickListener {
            viewModel.searchJokes(binding.searchView.query.toString())
        }
    }

    private fun initRecyclerview() {
        adapter = SearchJokeAdapter(this)

        binding.rvJokes.addItemDecoration(SpacesItemDecoration(0F, 0F, 6F, 0F))
        binding.rvJokes.adapter = adapter
        binding.rvJokes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                hideKeyboard()
            }
        })


        waitForTransition(binding.rvJokes)
    }

    private fun initViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.jokes.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initSearch() {
        val searchManager =
            requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        binding.searchView.imeOptions =
            binding.searchView.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                    EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        if (!isFragmentFromPaused) {
            binding.searchView.requestFocus()
            showSoftKeyboard(binding.searchView)
        }
        binding.searchBack.setOnClickListener {
            hideKeyboard()
            requireActivity().onBackPressed()
        }
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchJokes(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (!isFragmentFromPaused) {
                    viewModel.searchJokes(query)
                }
                return true
            }
        })
    }

    override fun onJokeItemClick(
        position: Int,
        value: ModelJoke,
        cardView: CardView,
        textView: TextView
    ) {
        hideKeyboard()
        val action = SearchFragmentDirections.actionSearchFragmentToDetailSliderFragment(
            ModelSearch(viewModel.jokes.value!!, viewModel.jokes.value!!.size),
            position
        )
        findNavController()
            .navigate(
                action,
                FragmentNavigator.Extras.Builder()
                    .addSharedElements(
                        mapOf(
                            cardView to cardView.transitionName,
                            textView to textView.transitionName
                        )
                    ).build()
            )
//        findNavController().addOnDestinationChangedListener(NavController.OnDestinationChangedListener(
//            { controller, destination, arguments ->
//                destination.
//            }))
    }
}