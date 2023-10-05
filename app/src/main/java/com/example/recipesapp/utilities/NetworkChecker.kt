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
     * Performs the specified action if a valid internet connection is available.
     *
     * @param action The action to perform if a valid internet connection is available.
     */
    fun performAction(action: () -> Unit) {
        if (hasValidInternetConnection()) {
            action()
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





