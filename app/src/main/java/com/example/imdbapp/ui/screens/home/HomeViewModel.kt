package com.example.imdbapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.imdbapp.models.MovieResponse
import com.example.imdbapp.models.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private var _responseState = MutableStateFlow<Boolean>(false)
    val responseState get(): MutableStateFlow<Boolean> = _responseState

    fun updateResponseState(success: Boolean) {
        _responseState.update {
            success
        }
    }

}