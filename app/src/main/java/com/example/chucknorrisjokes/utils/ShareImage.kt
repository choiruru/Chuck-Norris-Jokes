package com.example.chucknorrisjokes.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import java.io.ByteArrayOutputStream

object ShareImage {

    fun share(view:View, context: Context){
        val bitmap:Bitmap = ShareImage.getBitmapFromView(view)
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_STREAM, ShareImage.getImageUri(context, bitmap))
        shareIntent.type = "image/jpeg"
        context.startActivity(shareIntent)
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

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
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