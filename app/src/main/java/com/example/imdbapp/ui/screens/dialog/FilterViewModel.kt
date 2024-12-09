package com.example.imdbapp.ui.screens.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.common.util.searchedList
import com.example.imdbapp.common.util.token
import com.example.imdbapp.data.models.Movies
import com.example.imdbapp.data.repository.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val mainRepo: MainRepo
): ViewModel() {





}