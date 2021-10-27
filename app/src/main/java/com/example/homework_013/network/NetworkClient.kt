package com.example.homework_013.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkClient {

    const val BASE_URL = "https://run.mocky.io/v3/"


    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .build()

    }

    private fun moshi() =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()



    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }

}