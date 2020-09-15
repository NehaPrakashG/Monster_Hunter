package com.example.monsterhunter.Interface

import com.example.monsterhunter.Model.ArmorDataResponse

interface IarmView {

    interface View {

        fun showProgress(); //shows progress bar on the screen

        fun hideProgress();//hides progress bar on the screen

        fun setDataToRecyclerView(armList: ArrayList<ArmorDataResponse>); // sets list data to recyclerview

    }

    interface Presenter {

        fun onDestroy();

        fun requestDataFromServer();


    }
}