package com.example.chucknorrisjokes.ui.main

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentMainBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import com.example.chucknorrisjokes.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_main.btnShare
import kotlinx.android.synthetic.main.fragment_main.toolbar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.lyt_joke_share.*
import kotlinx.android.synthetic.main.lyt_offline.view.*
import java.io.ByteArrayOutputStream
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

        btnShare.setOnClickListener {
            shareImage(lytShare)
        }
        lytOffline.btnRetry.setOnClickListener {
            viewModel.start()
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
                val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity(),
                    searchMenuView, getString(R.string.transition_search_back)).toBundle()

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

    private fun shareImage(view:View){
        val bitmap:Bitmap = getBitmapFromView(view)
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(requireContext(), bitmap))
        shareIntent.type = "image/jpeg"
        startActivity(shareIntent)
    }

    private fun getBitmapFromView(view: View): Bitmap {
        //Define a bitmap with the same size as the view
        val returnedBitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas)
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        }
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
        return returnedBitmap
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        try {
            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

            @Suppress("DEPRECATION")
            val path = MediaStore.Images.Media.insertImage(
                inContext.getContentResolver(),
                inImage, "", ""
            )
            return Uri.parse(path)
        } catch (e: Exception) {
            e.message
        }
        return null
    }
}