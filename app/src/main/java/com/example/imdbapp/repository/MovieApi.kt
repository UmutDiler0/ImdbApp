package com.example.imdbapp.repository

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("imdb/imdbSearchByName")
    fun getMoviesByName(
        @Header("Authorization") token: String,
        @Query("query") movieName: String,
        @Query("year") year: String,
        @Query("type") type: String
    )

}