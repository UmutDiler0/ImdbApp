package com.example.imdbapp.ui.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbapp.databinding.SearchingItemBinding

class SearchingAdapter(val list: MutableList<String>): RecyclerView.Adapter<SearchingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SearchingItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieName: String){
            binding.searchingTV.text = movieName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

}