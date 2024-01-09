package com.vikanshu.core_ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        NetworkAvailable, NetworkUnavailable
    }
}

@SuppressLint("MissingPermission")
class ConnectivityObserverImpl(
    context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var currentNetworkState =
        if (connectivityManager.activeNetwork != null) ConnectivityObserver.Status.NetworkAvailable else ConnectivityObserver.Status.NetworkUnavailable

    override fun observe(): Flow<ConnectivityObserver.Status> {
        return callbackFlow {

            launch { send(currentNetworkState) }

            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    currentNetworkState = ConnectivityObserver.Status.NetworkAvailable
                    launch { send(currentNetworkState) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    currentNetworkState = ConnectivityObserver.Status.NetworkUnavailable
                    launch { send(currentNetworkState) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    currentNetworkState = ConnectivityObserver.Status.NetworkUnavailable
                    launch { send(currentNetworkState) }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}