package com.example.serviapp.ui.main

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Anil Gudigar on 25,September,2020
 */
class NetworkUtils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (connectivityManager != null) {
                val networkInfo = connectivityManager.activeNetworkInfo
                networkInfo != null && networkInfo.isConnected
            } else {
                false
            }
        }

    }
}