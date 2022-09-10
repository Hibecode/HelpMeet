package com.example.helpmeet.login.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.helpmeet.api.RetrofitInstance
import com.example.helpmeet.models.*
import com.example.helpmeet.utils.MyApp
import com.example.helpmeet.utils.Resource
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException


class LoginViewModel(
    app: Application
): AndroidViewModel(app) {

    val estateRegResponse: MutableLiveData<Resource<Estate>> = MutableLiveData()
    val userRegResponse: MutableLiveData<Resource<UserRegister>> = MutableLiveData()
    val estateListResponse: MutableLiveData<Response<List<SavedEstateDetails>>> = MutableLiveData()

    fun registerEstate(estateReg: Estate) = viewModelScope.launch {
        safeEstateRegCall(estateReg)
    }

    fun registerUser(userReg: UserRegister) = viewModelScope.launch {
        safeUserRegCall(userReg)
    }

    fun getEstateList() = viewModelScope.launch {
        val response = RetrofitInstance.api.getEstateList()
        estateListResponse.value = response
    }

    private fun handleUserReg(response: Response<UserRegister>): Resource<UserRegister> {
        if(response.isSuccessful) {
            response.body()?.let{
                return Resource.Success(it)
            }
        } else {
            //Error Response
            return try {
                val responseString = response.errorBody()?.string()
                val errorStr = JSONObject(responseString ?: "").toString()

                Resource.Error(errorStr)

            } catch (e: Exception) {
                Resource.Error("${e.message} ")
            }
        }
        return Resource.Error(response.message().toString())
    }

    private fun handleEstateReg(response: Response<Estate>): Resource<Estate> {
        if(response.isSuccessful) {
            response.body()?.let{
                return Resource.Success(it)
            }
        } else {
            //Error Response
            return try {

                val responseString = response.errorBody()?.string()
                val modelList = listOf("estate_country", "estate_name", "estate_address", "estate_admin")
                var errorStr = ""
                for (i in modelList) {
                    errorStr = JSONObject(responseString ?: "").toString()

                }

                Resource.Error(errorStr)

            } catch (e: Exception) {
                Resource.Error("${e.message} ")
            }
        }
        return Resource.Error(response.message().toString())
    }

    private suspend fun safeUserRegCall(userReg: UserRegister) {
        try {
            if (hasInternetConnection()) {
                val response = RetrofitInstance.api.registerUser(userReg)
                userRegResponse.postValue(handleUserReg(response))

            } else {
                userRegResponse.postValue(Resource.Error("No Internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> userRegResponse.postValue(Resource.Error("Network Failure"))
                else -> userRegResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }




    private suspend fun safeEstateRegCall(estateReg: Estate) {
        try {
            if (hasInternetConnection()) {
                val response = RetrofitInstance.api.registerEstate(estateReg)
                estateRegResponse.postValue(handleEstateReg(response))

            } else {
                estateRegResponse.postValue(Resource.Error("No Internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> estateRegResponse.postValue(Resource.Error("Network Failure"))
                else -> estateRegResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MyApp>().getSystemService(
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
    }
}