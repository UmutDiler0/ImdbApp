package com.example.imdbapp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.main.MainRepo
import com.example.imdbapp.models.Movies
import com.example.imdbapp.util.searchedList
import com.example.imdbapp.util.token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearcViewModel @Inject constructor(
    val mainRepo: MainRepo
): ViewModel() {

    private var _searchMovieList = MutableStateFlow<MutableList<Movies>>(mutableListOf())
    val searchMovieList get(): MutableStateFlow<MutableList<Movies>> = _searchMovieList

    private var _isSearched = MutableStateFlow<Boolean>(false)
    val isSearched get(): MutableStateFlow<Boolean> = _isSearched

    fun isUserSearched(searchedText: String?){
        viewModelScope.launch {
            if(mainRepo.isPromtSuccess){
                _searchMovieList.update {
                    mutableListOf()

                }
            }else{
                _searchMovieList.update {

                    mainRepo.searchMovieData(searchedText)
                }
                _isSearched.update {
                    true
                }
            }
        }

    }

}