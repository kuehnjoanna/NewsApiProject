package com.example.apicallsproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apicallsproject.data.model.Article
import com.example.apicallsproject.data.model.NewsResponse
import com.example.apicallsproject.data.remote.NewsApi

class NewsRepository {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles
    var _isWorking = MutableLiveData<Boolean>(false)
    val isWorking: LiveData<Boolean>
        get() = _isWorking
    suspend fun loadNews() {
        try {

            val response = NewsApi.apiService.getAllNews()
            Log.d("ApiResponse", response.toString())
            var lol = response.articles.filter { !it.urlToImage.isNullOrEmpty() || !it.description.isNullOrEmpty() }

            _articles.postValue(lol)
        }catch (e: Exception){
            Log.d("ApiResponse", "${e.message}")
            _isWorking.postValue(true)
        }
    }
}