package com.example.imdbapp.common.util

import com.example.imdbapp.data.models.Movies

const val BASE_URL= "https://api.collectapi.com/"
const val token = "apikey 13oPEHrjMP0aSJ1PwOs5rr:3trrQTlWuFkn8Zgn7Kt0yi"

var moviesList = mutableListOf<Movies>()
var searchingList = mutableListOf<String>()
var searchedList = mutableListOf<Movies>()
var favoritedMovies = mutableListOf<Movies>()
var searchText: String? = null



