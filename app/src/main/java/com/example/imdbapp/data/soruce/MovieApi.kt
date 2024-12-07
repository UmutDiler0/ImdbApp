package com.example.imdbapp.data.soruce

import com.example.imdbapp.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * en tepede data di domain ui common şeklinde packgace oluşturulacak
 * data model repository source olacak
 * repo içerisinde movie repo olacak
 * source içerisinde movieApi olacak
 *
 *  di appmodule app container en dışta olacak
 *
 *  ui kısmında adapterlar için farklı bir package açılmaz ui içersinde olacak
 *
 *  util common içerisinde olacak
 *
 */
interface MovieApi {

    @GET("imdb/imdbSearchByName")
    suspend fun getMoviesByName(
        @Header("Authorization") token: String,
        @Query("query") movieName: String = "Avengers",
        @Query("year") year: String? = null,
        @Query("type") type: String? = null
    ): MovieResponse



}