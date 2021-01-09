package com.example.serviapp

import android.bluetooth.BluetoothAdapter
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serviapp.ui.main.BluetoothReceiver
import com.example.serviapp.ui.main.MainFragment
import com.example.serviapp.ui.main.NetworkReceiver


class MainActivity : AppCompatActivity() {
    // The BroadcastReceiver that tracks network connectivity changes.
    private lateinit var receiver: NetworkReceiver
    private lateinit var btReceiver: BluetoothReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        // Registers BroadcastReceiver to track network connection changes.
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        receiver = NetworkReceiver()
        this.registerReceiver(receiver, filter)

        /* var bluetoothAdapter: BluetoothAdapter? = null
         bluetoothAdapter = if (Build.VERSION.SDK_INT >= 18) {
             val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
             bluetoothManager.adapter
         } else BluetoothAdapter.getDefaultAdapter()
 */
        // Registers BroadcastReceiver to track network connection changes.
        val bluetoothFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        btReceiver = BluetoothReceiver()
        this.registerReceiver(btReceiver, bluetoothFilter)

    }


    override fun onDestroy() {
        super.onDestroy()
        receiver.debugUnregister
        btReceiver.debugUnregister
    }

}