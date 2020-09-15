package com.example.monsterhunter.NetworkUtils

import com.example.monsterhunter.Model.ArmorDataResponse
import retrofit2.Response


abstract class callbackR{
        abstract fun returnResult(response: Response<List<ArmorDataResponse>>)
        abstract fun returnError(message: String?)
    }
