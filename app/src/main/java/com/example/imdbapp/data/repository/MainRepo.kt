package com.example.imdbapp.data.repository

import android.util.Log
import com.example.imdbapp.data.models.Movies
import com.example.imdbapp.data.soruce.MovieApi
import com.example.imdbapp.common.util.token
import com.example.imdbapp.data.models.DetailItem
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
//                isResponseSuccess = false
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

    suspend fun getMoviesWithID(movieID: String): DetailItem?{

        return try{
            val response = movieApi.getMoviesByID(token,movieID)
            if(response.success){
                response.result
            }else{
                null
            }
        }catch (e:Exception){
            Log.i("MainRepo","${e}")
            null
        }
    }


}