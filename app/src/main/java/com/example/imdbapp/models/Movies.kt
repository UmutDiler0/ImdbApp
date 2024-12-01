package com.example.imdbapp.models

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("Poster") val poster: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String
)