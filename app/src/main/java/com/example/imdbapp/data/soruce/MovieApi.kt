package com.example.imdbapp.data.soruce

import com.example.imdbapp.data.models.DetailMovie
import com.example.imdbapp.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("imdb/imdbSearchByName/")
    suspend fun getMoviesByName(
        @Header("Authorization") token: String,
        @Query("query") movieName: String = "Avengers",
        @Query("year") year: String? = null,
        @Query("type") type: String? = null
    ): MovieResponse

    @GET("imdb/imdbSearchById/")
    suspend fun getMoviesByID(
        @Header("Authorization") token: String,
        @Query("movieId") imdbID: String
    ): DetailMovie

}