package com.example.imdbapp.ui.screens.search

import androidx.lifecycle.ViewModel
import com.example.imdbapp.main.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearcViewModel @Inject constructor(
    val mainRepo: MainRepo
): ViewModel() {

    private var _isSearched = MutableStateFlow<Boolean>(false)
    val isSearched get(): MutableStateFlow<Boolean> = _isSearched

    fun isUserSearched(){

    }

}