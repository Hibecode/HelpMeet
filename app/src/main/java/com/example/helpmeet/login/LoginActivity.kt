package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.lifecycle.ViewModelProvider
import com.example.helpmeet.HomeActivity
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityLoginBinding
import com.example.helpmeet.login.viewmodel.LoginViewModel
import com.example.helpmeet.login.viewmodel.LoginViewModelFactory
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: LoginViewModel

    private lateinit var email: TextInputLayout
    private lateinit var password: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNav()

        val viewModelFactory = LoginViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        email = binding.etEmail
        password = binding.etPassword





    }


    private fun setNav() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }

        binding.tvSignupText.setOnClickListener{
            val intent = Intent(this, ChooseRegistrationActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }
    }
}