package com.example.kodehauz.`interface`

import com.example.kodehauz.model.News
import com.example.kodehauz.model.Website
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import java.net.URL

interface NewsService {


    @get:GET("v2/top-headlines/sources?apiKey=6a35c8c59dbc46988b3f836d6c2f8453")
    val sources: Call<Website>


    @GET
    fun getNewsFromSource(@Url url: String): Call<News>

}