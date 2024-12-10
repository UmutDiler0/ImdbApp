package com.example.imdbapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import com.example.imdbapp.data.repository.MainRepo
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    val mainRepo: MainRepo
): ViewModel() {
}