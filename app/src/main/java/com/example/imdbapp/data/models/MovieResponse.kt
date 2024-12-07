package com.example.imdbapp.data.models

data class MovieResponse(
    val result: List<Movies>,
    val success: Boolean
)