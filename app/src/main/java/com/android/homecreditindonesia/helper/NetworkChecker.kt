package com.android.homecreditindonesia.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkChecker(private val context: Context) {

    val isConnected: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            activeNetwork?.let {
                return it.isConnected
            }
            return false
        }

    val isNotConnected: Boolean
        get() = !isConnected
}