package com.example.imdbapp.main

import android.annotation.SuppressLint
import com.example.imdbapp.adapters.HomeAdapter
import com.example.imdbapp.models.MovieResponse
import com.example.imdbapp.repository.MovieApi
import com.example.imdbapp.util.moviesList
import com.example.imdbapp.util.token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepo @Inject constructor(
    val movieApi: MovieApi
) {

    fun fetchData(adapter: HomeAdapter){
        val call = movieApi.getMoviesByName(token)

        call.enqueue(
            object: Callback<MovieResponse>{
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            moviesList.clear()
                            moviesList.addAll(it.result)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(p0: Call<MovieResponse>, p1: Throwable) {
                    println("")
                }

            }
        )
    }
}