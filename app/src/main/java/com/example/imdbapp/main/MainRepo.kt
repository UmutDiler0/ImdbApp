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
    var searchedMovieList: MutableList<Movies> = mutableListOf()
    var isResponseSuccess = false
    var isPromtSuccess = false

    suspend fun fetchData(): MutableList<Movies> {

        return try{
            val response = movieApi.getMoviesByName(token)
            if (response.result.isNotEmpty()) {
                apiList = response.result.toMutableList()
                isResponseSuccess = false
                apiList
            } else {
                mutableListOf()
            }
        }catch(e: Exception){
            Log.i("MainRepo","${e}")
            mutableListOf()
        }

    }

    suspend fun searchMovieData(prompt: String?): MutableList<Movies>{
        return try{
            val response = movieApi.getMoviesByName(token,prompt!!)
            if (response.result.isNotEmpty()) {
                searchedMovieList.clear()
                searchedMovieList = response.result.toMutableList()
//                isPromtSuccess = true
                searchedMovieList
            } else {
                mutableListOf()
            }
        }catch(e: Exception){
            Log.i("MainRepo","${e}")
            mutableListOf()
        }
    }

}