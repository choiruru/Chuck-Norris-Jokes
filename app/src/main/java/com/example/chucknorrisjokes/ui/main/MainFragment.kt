package com.example.chucknorrisjokes.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.chucknorrisjokes.BR
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentMainBinding
import com.example.chucknorrisjokes.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.btnShare
import kotlinx.android.synthetic.main.fragment_main.toolbar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.lyt_joke_share.*
import kotlinx.android.synthetic.main.lyt_offline.*
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class MainFragment @Inject constructor() :BaseFragment<FragmentMainBinding,MainViewModel>(){
    private val TAG = "MainFragment"

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewReady(savedInstance: Bundle?) {
        viewModel.getRandomJoke()
        binding.setVariable(BR.data, viewModel)
        btnShare.setOnClickListener {
            shareImage(lytShare)
        }
        btnRetry.setOnClickListener {
            viewModel.getRandomJoke()
        }

        (activity as MainActivity).setSupportActionBar(toolbar);
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