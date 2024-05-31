package com.example.apicallsproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apicallsproject.data.model.Article
import com.example.apicallsproject.data.model.NewsResponse
import com.example.apicallsproject.data.remote.NewsApi

class NewsRepository {
    private val _articles = MutableLiveData<NewsResponse>()
    val articles: LiveData<NewsResponse>
        get() = _articles
    var _isWorking = MutableLiveData<Boolean>(false)
    val isWorking: LiveData<Boolean>
        get() = _isWorking
    suspend fun loadNews() {
        try {

            val response = NewsApi.apiService.getAllNews()
            Log.d("ApiResponse", response.toString())

            _articles.postValue(response)
        }catch (e: Exception){
            Log.d("ApiResponse", "${e.message}")
            _isWorking.postValue(true)
        }
    }
}