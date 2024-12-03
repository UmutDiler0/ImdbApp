package com.example.imdbapp.main

import android.util.Log
import com.example.imdbapp.models.Movies
import com.example.imdbapp.repository.MovieApi
import com.example.imdbapp.util.token
import javax.inject.Inject

class MainRepo @Inject constructor(
    val movieApi: MovieApi
) {

    var apiList: MutableList<Movies> = mutableListOf()
    var isResponseSuccess = false

    suspend fun fetchData(): MutableList<Movies> {

        return try{
            val response = movieApi.getMoviesByName(token)
            if (response.result.isNotEmpty()) {
                apiList = response.result.toMutableList()
                isResponseSuccess = true
                apiList
            } else {
                mutableListOf()
            }
        }catch(e: Exception){
            Log.i("MainRepo","${e}")
            mutableListOf()
        }

    }

}