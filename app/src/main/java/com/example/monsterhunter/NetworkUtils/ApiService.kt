package com.example.monsterhunter.NetworkUtils

import com.example.monsterhunter.Model.ArmorDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("armor")
    fun getArmor(): Call<List<ArmorDataResponse>>
}