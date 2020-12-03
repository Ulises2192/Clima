package com.ulisesdiaz.clima.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class NetWork {

    companion object{

        fun isNetwork(activity: AppCompatActivity):Boolean{
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netWorkInfo = connectivityManager.activeNetworkInfo
            return netWorkInfo != null && netWorkInfo.isConnected
        }
    }
}