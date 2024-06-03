package com.example.apicallsproject.ui

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicallsproject.data.Datasource
import com.example.apicallsproject.data.NewsRepository
import com.example.apicallsproject.data.model.Article
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val repository = NewsRepository()
    var articles = repository.articles


    val allFavProfiles: MutableList<Article> = Datasource.favorites.toMutableList()
    private val _favorites = MutableLiveData<List<Article>>(allFavProfiles)
    val favorites: LiveData<List<Article>>
        get() = _favorites

    val allFilteredProfiles: MutableList<Article> = Datasource.filtered.toMutableList()
    private val _filtered = MutableLiveData<List<Article>>(allFilteredProfiles)
    val filtered: LiveData<List<Article>>
        get() = _filtered





    val selectedArticle = MutableLiveData<Article>()



    fun selectedArticleItem(it: Article){
        selectedArticle.value = it
    }
    fun loadImages() {
        viewModelScope.launch {

            repository.loadNews()

        }
    }
    fun addToFav(it:Article){
allFavProfiles.add(selectedArticle.value!!)
    }

    fun filterArticles(searchTerm: String){

        val newFiltered = articles.value!!.filter {
            it.description!!.contains(searchTerm) || it.content!!.contains(searchTerm)|| it.title!!.contains(searchTerm)
        }
        for (i in newFiltered){
            allFilteredProfiles.add(i)
        }

    }
}