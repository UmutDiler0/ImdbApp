package com.example.imdbapp.adapters

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbapp.databinding.FragmentHomeBinding
import com.example.imdbapp.databinding.HomeItemBinding
import com.example.imdbapp.models.Movies

class HomeAdapter(val list: List<Movies>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(binding: HomeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movies: Movies){
            TODO()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

}