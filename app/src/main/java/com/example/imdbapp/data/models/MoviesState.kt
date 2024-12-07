package com.example.imdbapp.data.models

import com.google.gson.annotations.SerializedName

data class MoviesState(
    val poster: String,
    val title: String,
    val type: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String
)