package com.example.serviapp.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { NetworkUtils.isNetworkAvailable(context) }?.let {
            MainViewModel.isWifiConnected.value = it
        }
    }
}