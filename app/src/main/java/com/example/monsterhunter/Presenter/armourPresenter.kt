package com.example.monsterhunter.Presenter

import android.util.Log
import com.example.monsterhunter.Interface.IarmView
import com.example.monsterhunter.Model.ArmorDataResponse
import com.example.monsterhunter.NetworkUtils.ApiService
import com.example.monsterhunter.NetworkUtils.RetrofitAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class armourPresenter(var view: IarmView.View): IarmView.Presenter{
    private var ArmmainView: IarmView.View? = null

    override fun onDestroy() {
        ArmmainView= null
    }

    override fun requestDataFromServer() {
        ArmmainView = view;
        ArmmainView!!.showProgress()
        val api = RetrofitAdapter.buildService(ApiService::class.java).getArmor();
        api.enqueue(object : Callback<List<ArmorDataResponse>> {
            override fun onResponse(
                call: Call<List<ArmorDataResponse>>,
                response: Response<List<ArmorDataResponse>>
            ) {
                Log.d("armor", response.message())
                ArmmainView!!.setDataToRecyclerView(response.body() as ArrayList<ArmorDataResponse>)
                ArmmainView!!.hideProgress()
            }

            override fun onFailure(call: Call<List<ArmorDataResponse>>, t: Throwable) {
                Log.d("armor", "onFailure")
            }
        })

    }
}