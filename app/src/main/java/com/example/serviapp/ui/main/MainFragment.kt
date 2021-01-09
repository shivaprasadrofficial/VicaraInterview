package com.example.serviapp.ui.main

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.serviapp.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var wifi: TextView
    private lateinit var bluetooth: TextView

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        wifi = view.findViewById(R.id.wifiTextview)
        bluetooth = view.findViewById(R.id.bluetoothTextview)
//        val binding: MainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        MainViewModel.isBluetoothConnected.observe(this, Observer {
            when (it) {
                BluetoothAdapter.STATE_OFF -> setButtonText("Bluetooth off")
                BluetoothAdapter.STATE_TURNING_OFF -> setButtonText("Turning Bluetooth off...")
                BluetoothAdapter.STATE_ON -> setButtonText("Bluetooth on")
                BluetoothAdapter.STATE_TURNING_ON -> setButtonText("Turning Bluetooth on...")
            }
        })
        MainViewModel.isWifiConnected.observe(this, Observer {
            wifi.text = if (it) "Wifi Connected " else "Wifi disconnected"
        })
    }

    fun setButtonText(string: String) {
        bluetooth.text = string
    }

    /* override fun isNetworkActive(isActive: Boolean) {
         viewModel.isWifiConnected?.value = isActive
     }

     override fun bluetoothState(state: Int) {
         viewModel.isBluetoothConnected?.value = state
     }*/

}