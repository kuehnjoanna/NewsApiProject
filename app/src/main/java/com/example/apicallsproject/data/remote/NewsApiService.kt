package com.example.apicallsproject.data.remote

import com.example.apicallsproject.data.model.NewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale.IsoCountryCode

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
    suspend fun getAllNews():NewsResponse

   @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):NewsResponse
}

object NewsApi {
    val apiService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}