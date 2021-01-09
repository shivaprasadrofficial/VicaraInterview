package com.example.serviapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    companion object {
        var isWifiConnected: MutableLiveData<Boolean> = MutableLiveData()
        var isBluetoothConnected: MutableLiveData<Int> = MutableLiveData()
    }

}