package com.example.chucknorrisjokes.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.model.ModelJoke
import com.example.chucknorrisjokes.databinding.ItemJokeBinding

import javax.inject.Inject

class SearchJokeAdapter @Inject constructor(
    private var models : LiveData<MutableList<ModelJoke>>,
    private var onItemClick:OnJokeItemClickListener
) : RecyclerView.Adapter<SearchJokeAdapter.JokeViewHolder>() {
    private val TAG = "SearchJokeAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = DataBindingUtil.inflate<ItemJokeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_joke,
            parent,
            false
        )
        return JokeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if(models.value == null){
            return 0
        }
        return models.value!!.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.binding.modelJoke = models.value!![position]
        holder.binding.lytRoot.setOnClickListener {
            onItemClick.onJokeItemClick(models.value!![position])
        }
    }

    interface OnJokeItemClickListener {
        fun onJokeItemClick(value:ModelJoke)
    }

    inner class JokeViewHolder(var binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root)
}