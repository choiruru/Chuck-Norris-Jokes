package com.example.chucknorrisjokes.ui.detail_slider

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.databinding.ItemJokeCarouselBinding
import javax.inject.Inject

class DetailSliderAdapter @Inject constructor() : ListAdapter<ModelJoke, DetailSliderAdapter.SliderHolder>(object : DiffUtil.ItemCallback<ModelJoke>(){
    override fun areItemsTheSame(oldItem: ModelJoke, newItem: ModelJoke): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelJoke, newItem: ModelJoke): Boolean {
        return oldItem.id == newItem.id
    }

}) {
    private val TAG = "SearchJokeAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  SliderHolder {
        val binding = DataBindingUtil.inflate<ItemJokeCarouselBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_joke_carousel,
            parent,
            false
        )
        return SliderHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class SliderHolder(var binding: ItemJokeCarouselBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ModelJoke) {
            binding.modelJoke = item
            binding.executePendingBindings()
        }
    }
}