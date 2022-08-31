package com.example.helpmeet.login.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.helpmeet.api.RetrofitInstance
import com.example.helpmeet.models.ErrorResponse
import com.example.helpmeet.models.Estate
import com.example.helpmeet.models.SavedEstateDetails
import com.example.helpmeet.models.UserRegister
import com.example.helpmeet.utils.MyApp
import com.example.helpmeet.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

    private fun handleEstateReg(response: Response<Estate>): Resource<Estate> {
        if(response.isSuccessful) {
            response.body()?.let{
                return Resource.Success(it)
            }
        } else /*if (response.code() == 400)*/{
            return try {
                val gson = Gson()
                /*val type = object : TypeToken<Response<Estate>>() {}.type
                var errorResponse: Estate? = gson.fromJson(response.errorBody()?.charStream(), type)
*/
                /*val error = gson.fromJson(response.raw(), Estate::class.java)

                val jObjError = JSONObject(response.errorBody()!!.string())
                Log.d("vmtestes", error.estate_address[0].toString())
                Resource.Error(jObjError.getJSONObject("estate_name").getString("message"))*/
                Log.d("vmtestes", response.errorBody()?.close().toString())
                Resource.Error(response.body().toString())
            } catch (e: Exception) {
                Resource.Error("${e.message} from catchpoint boy")
            }

        }
        return Resource.Error(response.message().toString())

    }

    private suspend fun safeUserRegCall(userReg: UserRegister) {
        try {
            if (hasInternetConnection()) {
                val response = RetrofitInstance.api.registerUser(userReg)
                //estateRegResponse.postValue(handleUserReg(response))

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