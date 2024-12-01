package com.example.imdbapp.repository

import com.example.imdbapp.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("imdb/imdbSearchByName")
    fun getMoviesByName(
        @Header("Authorization") token: String,
        @Query("query") movieName: String? = null,
//        @Query("year") year: String,
//        @Query("type") type: String
    ): Call<MovieResponse>

}