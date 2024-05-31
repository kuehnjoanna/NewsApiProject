package com.example.apicallsproject.data.remote

import com.example.apicallsproject.data.model.NewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val API_KEY = "34131f3f211b4ca69a5dcc638bb2997d"
const val BASE_URL = "https://newsapi.org/"

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(
        BASE_URL
    ).build()

interface NewsApiService {

    @GET("v2/top-headlines?country=us&apiKey=34131f3f211b4ca69a5dcc638bb2997d")
    suspend fun getNews(): NewsResponse
}

object NewsApi {
    val apiService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}