package com.example.imdbapp.common.util

import androidx.navigation.NavDeepLinkRequest
import com.example.imdbapp.BuildConfig
import com.example.imdbapp.data.models.Movies

const val BASE_URL= "https://api.collectapi.com/"
const val token = BuildConfig.API_KEY

var moviesList = mutableListOf<Movies>()
var searchedList = mutableListOf<Movies>()
var favoritedMovies = mutableListOf<Movies>()
var searchText: String? = null



