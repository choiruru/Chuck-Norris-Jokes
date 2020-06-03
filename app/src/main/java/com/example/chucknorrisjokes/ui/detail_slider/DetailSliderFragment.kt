package com.example.chucknorrisjokes.ui.detail_slider

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentDetailSliderBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.utils.ShareImage
import javax.inject.Inject


class DetailSliderFragment @Inject constructor() : BaseFragment<FragmentDetailSliderBinding, SliderViewModel>() {
    private val TAG = "DetailSliderFragment"

    private val args : DetailSliderFragmentArgs by navArgs()
    lateinit var adapter: DetailSliderAdapter
    lateinit var layoutManager: CarouselLayoutManager

    override fun getViewModelClass(): Class<SliderViewModel> {
        return SliderViewModel::class.java
    }

    override fun getLayoutId(): Int {
       return R.layout.fragment_detail_slider
    }

    override fun onViewReady(savedInstance: Bundle?) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        postponeEnterTransition()
        initViewModel()

        binding.btnShare.setOnClickListener {
            val view = binding.rvJokes.findViewHolderForAdapterPosition(layoutManager.centerItemPosition)!!.itemView
            ShareImage.share(view.findViewById(R.id.lytRoot), requireContext())
        }

        binding.toolbar.setOnMenuItemClickListener {
            requireActivity().onBackPressed()
            true
        }
    }

    private fun initViewModel(){
        adapter = DetailSliderAdapter()
        layoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false)
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
        layoutManager.maxVisibleItems = 2

        binding.rvJokes.layoutManager = layoutManager
        binding.rvJokes.setHasFixedSize(true)
        binding.rvJokes.adapter = adapter
        binding.rvJokes.addOnScrollListener(CenterScrollListener());


        binding.viewModel = viewModel
        viewModel.setData(args.models.result)
        viewModel.models.observe(this, Observer {
            adapter.submitList(it)
            binding.rvJokes.scrollToPosition(args.position)
            binding.rvJokes.post {
                val view = binding.rvJokes.findViewHolderForAdapterPosition(args.position)!!.itemView
                view.viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
            }
        })
    }
}