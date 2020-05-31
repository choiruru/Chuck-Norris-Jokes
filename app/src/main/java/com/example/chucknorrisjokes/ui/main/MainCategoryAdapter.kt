package com.example.chucknorrisjokes.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.ItemCategoryBinding

import javax.inject.Inject

class MainCategoryAdapter @Inject constructor(
    private var models : LiveData<MutableList<String>>,
    private var onItemClick:OnCategoryItemClickListener
) : RecyclerView.Adapter<MainCategoryAdapter.CategoryViewHolder>() {
    private val TAG = "MainCategoryAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemCategoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_category,
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if(models.value == null){
            return 0
        }
        Log.d(TAG, "SIZE: "+ models.value!!.size)
        return models.value!!.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.category = models.value!![position]
        holder.binding.root.setOnClickListener {
            onItemClick.onCategoryItemClick(models.value!![position])
        }
    }

    interface OnCategoryItemClickListener {
        fun onCategoryItemClick(value:String)
    }

    inner class CategoryViewHolder(var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}