package com.example.helpmeet.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.helpmeet.databinding.ActivityHomeBinding
import com.example.helpmeet.databinding.TestPageBinding
import com.example.helpmeet.login.viewmodel.LoginViewModel
import com.example.helpmeet.login.viewmodel.LoginViewModelFactory

class TestActivity : AppCompatActivity() {

    private lateinit var binding: TestPageBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TestPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModelFactory = LoginViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        viewModel.getEstateList()
        viewModel.estateListResponse.observe(this) { response ->
            if (response.isSuccessful) {
                binding.testText.text = response.body()?.get(2)?.member?.email.toString()
            } else {
                Log.d("test", response.message().toString())
            }
        }
    }
}