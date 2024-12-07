package com.example.imdbapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.data.models.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val mainRepo: MainRepo,
) : ViewModel() {

    private var _movieList = MutableStateFlow<List<Movies>>(mutableListOf())
    val movieListVM get(): MutableStateFlow<List<Movies>> = _movieList
    private var _responseState = MutableStateFlow<Boolean>(false)
    val responseState get(): MutableStateFlow<Boolean> = _responseState

    fun loadMovies(){

        viewModelScope.launch{
            if(mainRepo.isResponseSuccess){
                _movieList.update {
                    mutableListOf()
                }

            }else{
                _movieList.update {
                    mainRepo.fetchData()
                }
                _responseState.update {
                    true
                }

            }
        }
    }

}