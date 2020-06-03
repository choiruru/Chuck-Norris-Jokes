package com.example.chucknorrisjokes.ui.search

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
import com.example.chucknorrisjokes.databinding.ItemJokeBinding

import javax.inject.Inject

class SearchJokeAdapter @Inject constructor(
    private var onItemClick:OnJokeItemClickListener
) : ListAdapter<ModelJoke,  SearchJokeAdapter.JokeViewHolder>(object : DiffUtil.ItemCallback<ModelJoke>(){
    override fun areItemsTheSame(oldItem: ModelJoke, newItem: ModelJoke): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelJoke, newItem: ModelJoke): Boolean {
        return oldItem.id == newItem.id
    }

}) {
    private val TAG = "SearchJokeAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  JokeViewHolder {
        val binding = DataBindingUtil.inflate<ItemJokeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_joke,
            parent,
            false
        )
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.binding.lytRoot.setOnClickListener {
            Log.d(TAG, "CLICK: "+getItem(position));
            onItemClick.onJokeItemClick(position, getItem(position), holder.binding.lytRoot, holder.binding.txtItemJoke)
        }
        holder.bind(getItem(position))

    }

    interface OnJokeItemClickListener {
        fun onJokeItemClick(position:Int, value:ModelJoke, cardView: CardView, textView: TextView)
    }

    inner class JokeViewHolder(var binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ModelJoke) {
            binding.modelJoke = item
            binding.executePendingBindings()
        }
    }
}