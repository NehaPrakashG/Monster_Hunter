package com.example.monsterhunter.NetworkUtils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAdapter {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mhw-db.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}