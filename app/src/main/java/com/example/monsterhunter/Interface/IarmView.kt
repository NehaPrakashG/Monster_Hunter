package com.example.monsterhunter.Interface

import com.example.monsterhunter.Model.ArmorDataResponse

interface IarmView {
    interface Model {

     //   fun getArmour(OnFinishedListener onFinishedListener);  //creates list of fibonacci elements 0>n<=1000


        interface OnFinishedListener {
       //     fun onFinished(List<ModelFibo> fiboArrayList); //returns list of fibonacci elements once all elements added

        //    fun onFailure(Throwable t); //chceks if views are null
        }


    }

    interface View {

        fun showProgress(); //shows progress bar on the screen

        fun hideProgress();//hides progress bar on the screen

        fun setDataToRecyclerView(armList: ArrayList<ArmorDataResponse>); // sets list data to recyclerview

//
    }

    interface Presenter {

        fun onDestroy();

        fun getMoreData();

        fun requestDataFromServer();


    }
}