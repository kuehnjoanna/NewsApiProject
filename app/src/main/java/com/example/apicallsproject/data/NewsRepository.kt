package com.example.apicallsproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apicallsproject.data.model.Article
import com.example.apicallsproject.data.model.NewsResponse
import com.example.apicallsproject.data.remote.NewsApi

class NewsRepository {

    //lists that will be later posted in ui
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    //boolean that is supposed to work according to internet connection
    var _isWorking = MutableLiveData<Boolean>(true)
    val isWorking: LiveData<Boolean>
        get() = _isWorking

    //news loading function
    suspend fun loadNews() {
        try {
            //getting all news from web
            val response = NewsApi.apiService.getAllNews()
            Log.d("ApiResponse", response.toString())
            //filtering list from web and adjusting boolean
            val filteredArticles = response.articles.filter { !it.urlToImage.isNullOrEmpty() && !it.url.isNullOrEmpty() || !it.description.isNullOrEmpty() && !it.url.isNullOrEmpty() }
          _isWorking.postValue(true)

            //
            _articles.postValue(filteredArticles)
        }catch (e: Exception){
            Log.d("ApiResponse", "${e.message}")
            _isWorking.postValue(false)
        }
    }
}