package com.example.helpmeet.login.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.helpmeet.api.RetrofitInstance
import com.example.helpmeet.models.Estate
import retrofit2.Response

class LoginViewModel(): ViewModel() {

    val estateRegResponse: MutableLiveData<Response<Estate>> = MutableLiveData()

    fun registerEstate(estateReg: Estate) = viewModelScope.launch {
        val response = RetrofitInstance.api.registerEstate(estateReg)
        estateRegResponse.value = response
    }


   /* private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<AlbumApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }*/
}