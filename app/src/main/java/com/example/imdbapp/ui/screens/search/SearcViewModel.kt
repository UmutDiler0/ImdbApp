package com.example.imdbapp.ui.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.common.util.searchText
import com.example.imdbapp.common.util.searchedList
import com.example.imdbapp.common.util.token
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.data.models.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
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

    private var _isFiltered = MutableStateFlow<Boolean>(false)
    val isFiltered: MutableStateFlow<Boolean> get() = _isFiltered

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

    fun getFilteredList(applyYear: String?, applyType: String?) {

        viewModelScope.launch {
            val movieList: MutableList<Movies> = mutableListOf()
            val filteredList: MutableList<Movies> = mutableListOf()

            movieList.addAll(_searchMovieList.value)

            filteredList.addAll(
                movieList.filter {
                    (it.year.equals(applyYear) || (it.type.equals(applyType)))
                }
            )
            _isFiltered.value = true

            _filteredList.update{
                filteredList
            }
        }
    }

    fun setFilteredFalse(){
        viewModelScope.launch{
            _isFiltered.update{
                false
            }
        }
    }
}

