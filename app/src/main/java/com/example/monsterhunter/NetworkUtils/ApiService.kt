package com.example.monsterhunter.NetworkUtils

import com.example.monsterhunter.Model.ArmorDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("armor")
    fun getArmor(): Call<List<ArmorDataResponse>>
}