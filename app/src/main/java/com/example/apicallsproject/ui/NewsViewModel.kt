package com.example.apicallsproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicallsproject.data.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val repository = NewsRepository()

    val articles = repository.articles

    fun loadImages() {
        viewModelScope.launch {

            repository.loadNews()

        }
    }
}