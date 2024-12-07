package com.example.imdbapp.ui.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imdbapp.R
import com.example.imdbapp.common.util.favoritedMovies
import com.example.imdbapp.databinding.HomeItemBinding
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.data.models.Movies


class HomeAdapter(
    var list: List<Movies>,
    val mainRepo: MainRepo,
): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: HomeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movies: Movies){
            if(!mainRepo.isResponseSuccess){
                with(binding){
                    movieNameTV.text = movies.title
                    movieTypeTV.text = movies.type
                    movieYearTV.text = movies.year
                    progressBar.visibility = View.GONE
                    favoritedMovies.forEach{
                        if(it.imdbID == movies.imdbID){
                            binding.favBtn.setImageResource(R.drawable.ic_fav)
                        }
                    }
                }
                Glide.with(itemView.context)
                    .load(movies.poster)
                    .apply(RequestOptions.circleCropTransform())
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
        holder.bind(list[position])
        var isClicked = false
        holder.binding.favBtn.setOnClickListener {
            if(isClicked){
                holder.binding.favBtn.setImageResource(R.drawable.ic_unfav)
                if(favoritedMovies.contains(list[position])){
                    favoritedMovies.remove(list[position])
                    favoritedMovies.forEach {
                        if(it.imdbID == list[position].imdbID){
                            it.isFavorite = false
                        }
                    }
                }
                isClicked = false
            }else{
                holder.binding.favBtn.setImageResource(R.drawable.ic_fav)
                if(!favoritedMovies.contains(list[position])){
                    favoritedMovies.add(list[position])
                    favoritedMovies.forEach {
                        if(it.imdbID == list[position].imdbID){
                            it.isFavorite = true
                        }
                    }
                }
                isClicked = true
            }
        }
    }

}