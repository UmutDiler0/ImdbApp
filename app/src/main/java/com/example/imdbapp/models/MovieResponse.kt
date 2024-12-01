package com.example.imdbapp.models

data class MovieResponse(
    val result: List<Movies>,
    val success: Boolean
)