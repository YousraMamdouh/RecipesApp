package com.example.recipesapp.utilities

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Utility class for checking the availability of a valid internet connection.
 *
 * @param connectivityManager The Android [ConnectivityManager] instance to use for network checking.
 */
class NetworkChecker(private val connectivityManager: ConnectivityManager) {
    /**
     * Performs actions based on the availability of a valid internet connection.
     *
     * @param actionIfConnected Lambda expression to execute when there is a valid internet connection.
     * @param actionIfNotConnected Lambda expression to execute when there is no valid internet connection.
     */
    fun performAction(actionIfConnected: () -> Unit, actionIfNotConnected: () -> Unit) {
        if (hasValidInternetConnection()) {
            actionIfConnected()
        } else {
            actionIfNotConnected()
        }
    }

    /**
     * Checks if a valid internet connection is available.
     *
     * @return `true` if a valid internet connection is available, otherwise `false`.
     */
    private fun hasValidInternetConnection(): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}





