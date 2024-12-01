package com.example.imdbapp.main

import com.example.imdbapp.repository.MovieApi
import javax.inject.Inject

class MainRepo @Inject constructor(
    val movieApi: MovieApi
) {


}