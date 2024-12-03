package com.example.imdbapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbapp.R
import com.example.imdbapp.databinding.HomeItemBinding
import com.example.imdbapp.main.MainRepo
import com.example.imdbapp.models.Movies


class HomeAdapter(var list: List<Movies>,val mainRepo: MainRepo): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: HomeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movies: Movies){
            if(!mainRepo.isResponseSuccess){
                with(binding){
                    movieNameTV.text = movies.title
                    movieTypeTV.text = movies.type
                    movieYearTV.text = movies.year
                    progressBar.visibility = View.GONE
                }
                Glide.with(itemView.context)
                    .load(movies.poster)
                    .error(R.drawable.ic_error)
                    .into(binding.movieIV)
            }else{
                binding.movieIV.setImageResource(R.drawable.ic_error)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position],)
        var isClicked = false
        holder.binding.favBtn.setOnClickListener {
            if(isClicked){
                holder.binding.favBtn.setImageResource(R.drawable.ic_unfav)
                isClicked = false
            }else{
                holder.binding.favBtn.setImageResource(R.drawable.ic_fav)
                isClicked = true
            }
        }
    }

}