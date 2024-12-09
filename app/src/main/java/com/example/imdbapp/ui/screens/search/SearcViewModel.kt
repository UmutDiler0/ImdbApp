package com.example.imdbapp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.common.util.searchText
import com.example.imdbapp.common.util.searchedList
import com.example.imdbapp.common.util.token
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.data.models.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearcViewModel @Inject constructor(
    val mainRepo: MainRepo
) : ViewModel() {

    private var _searchMovieList = MutableStateFlow<MutableList<Movies>>(mutableListOf())
    val searchMovieList get(): MutableStateFlow<MutableList<Movies>> = _searchMovieList

    private var _isSearched = MutableStateFlow<Boolean>(false)
    val isSearched get(): MutableStateFlow<Boolean> = _isSearched

    private var _filteredList = MutableStateFlow<MutableList<Movies>>(mutableListOf())
    val filteredList get(): MutableStateFlow<MutableList<Movies>> = _filteredList

    fun isUserSearched(searchedText: String?) {
        viewModelScope.launch {
            if (mainRepo.isPromtSuccess) {
                _searchMovieList.update {
                    mutableListOf()
                }
            } else {
                _searchMovieList.update {
                    mainRepo.searchMovieData(searchedText)
                }
                _isSearched.update {
                    true
                }
            }
        }
    }

    fun getFilteredList(applyYear: String?, applyType: String?){
        viewModelScope.launch {
            _filteredList.update {
                mainRepo.searchFilteredMovies( searchText!!,applyYear,applyType)
            }
        }
    }

}