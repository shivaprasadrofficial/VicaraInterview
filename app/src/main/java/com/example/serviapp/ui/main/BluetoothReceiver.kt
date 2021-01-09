package com.example.serviapp.ui.main

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.serviapp.R


class BluetoothReceiver : BroadcastReceiver() {
    val TAG = "BluetoohReceiver"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        Log.i(TAG, "onReceive: action = $action ")
        if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(
                BluetoothAdapter.EXTRA_STATE,
                BluetoothAdapter.ERROR
            )
            when (state) {
                BluetoothAdapter.STATE_OFF -> setNotification("Bluetooth off", context)
                BluetoothAdapter.STATE_TURNING_OFF -> setNotification(
                    "Turning Bluetooth off...",
                    context
                )
                BluetoothAdapter.STATE_ON -> setNotification("Bluetooth on", context)
                BluetoothAdapter.STATE_TURNING_ON -> setNotification(
                    "Turning Bluetooth on...",
                    context
                )
            }
            MainViewModel.isBluetoothConnected.value = state

        }
    }

    fun setNotification(string: String, context: Context) {
        val bm = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(
                context.resources,
                android.R.drawable.stat_sys_data_bluetooth
            ),
            context.resources.getDimensionPixelSize(R.dimen.notification_large_icon_width),
            context.resources.getDimensionPixelSize(R.dimen.notification_large_icon_height),
            true
        )
        val intent1 = Intent(context, MainViewModel::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
        var builder = NotificationCompat.Builder(context, "1017")
        builder.setContentTitle("Bluetooth Notification")
        builder.setContentTitle(string)
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(android.R.drawable.stat_sys_data_bluetooth)
        builder.setLargeIcon(bm)
        builder.setOngoing(true)
        val notification: Notification = builder.build()
        val notificationManger =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManger!!.notify(1017, notification)
    }

}