package com.example.imdbapp.ui.screens.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.common.util.favoritedMovies
import com.example.imdbapp.data.models.Movies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(

): ViewModel() {

    private var _favoritedMovieList= MutableStateFlow<MutableList<Movies>>(mutableListOf())
    val favoritedMovieList get(): MutableStateFlow<MutableList<Movies>> = _favoritedMovieList

    fun getFavoritedMovies(){
        viewModelScope.launch {
            _favoritedMovieList.update {
                it.addAll(favoritedMovies)
                it
            }
        }
    }

}