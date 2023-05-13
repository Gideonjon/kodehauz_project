package com.example.kodehauz.common

import com.example.kodehauz.`interface`.NewsService
import com.example.kodehauz.retrofit.RetrofitClient

object first {
    val BASE_URL = "https://newsapi.org/"
    val Api_Key = "6a35c8c59dbc46988b3f836d6c2f8453"


    val newsService: NewsService
        get() = RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)


    fun getNewsApi(source: String): String {
        val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?country=us")
            .append(source)
            .append("&apikey=")
            .append(Api_Key)
            .toString()


        return apiUrl
    }

}