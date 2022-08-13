package com.example.helpmeet.login.viewmodel

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
}