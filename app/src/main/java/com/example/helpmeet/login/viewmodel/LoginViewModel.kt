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
import com.example.helpmeet.models.SavedEstateDetails
import retrofit2.Response
import java.io.IOException

class LoginViewModel(): ViewModel() {

    val estateRegResponse: MutableLiveData<Response<Estate>> = MutableLiveData()
    val estateListResponse: MutableLiveData<Response<List<SavedEstateDetails>>> = MutableLiveData()

    fun registerEstate(estateReg: Estate) = viewModelScope.launch {
        val response = RetrofitInstance.api.registerEstate(estateReg)
        estateRegResponse.value = response
    }

    fun getEstateList() = viewModelScope.launch {
        val response = RetrofitInstance.api.getEstateList()
        estateListResponse.value = response
    }



    /*private suspend fun safeAlbumCall() {
        albumResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = repository.getTopAlbums()
                albumResponse.postValue(handleAlbumResponse(response))
            } else {
                albumResponse.postValue(Resource.Error("No Internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> albumResponse.postValue(Resource.Error("Network Failure"))
                else -> albumResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private suspend fun safeHomeAlbumCall() {
        albumResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = repository.getTopAlbums()
                albumResponse.postValue(handleAlbumResponse(response))
            } else {
                albumResponse.postValue(Resource.Error("No Internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> albumResponse.postValue(Resource.Error("Network Failure"))
                else -> albumResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }*/


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