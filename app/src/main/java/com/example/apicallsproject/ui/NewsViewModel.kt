package com.example.apicallsproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicallsproject.data.NewsRepository
import com.example.apicallsproject.data.model.Article
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val repository = NewsRepository()

    val articles = repository.articles
    val selectedArticle = MutableLiveData<Article>()



    fun selectedArticleItem(it: Article){
        selectedArticle.value = it
    }
    fun loadImages() {
        viewModelScope.launch {

            repository.loadNews()

        }
    }
}