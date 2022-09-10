package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.helpmeet.HomeActivity
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityLoginBinding
import com.example.helpmeet.login.viewmodel.LoginViewModel
import com.example.helpmeet.login.viewmodel.LoginViewModelFactory
import com.example.helpmeet.models.LoginModel
import com.example.helpmeet.models.UserRegister
import com.example.helpmeet.utils.Constants
import com.example.helpmeet.utils.Resource
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

        binding.loginButton.setOnClickListener {

            confirmInput()

        }

        viewModel.loginResponse.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    Toast.makeText(this, "WELCOME!", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    val errorMessage = response.message.toString()
                    separateErrors(errorMessage)
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> Toast.makeText(this,"", Toast.LENGTH_LONG).show()
            }


        })



    }


    private fun validateEmail(): Boolean {
        val emailInput = email.editText?.text.toString().trim()

        return if(emailInput.isEmpty()) {
            email.helperText = "Field can't be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.helperText = "Please enter a valid email address"
            false
        } else{
            email.helperText = null
            true
        }

    }

    private fun validatePassword(): Boolean{
        val passwordInput = password.editText?.text.toString().trim()

        return if( passwordInput.isEmpty()){
            password.helperText = "Field can't be empty"
            false
        } else if (!Constants.PASSWORD_PATTERN.matcher(passwordInput).matches()){
            password.helperText = Constants.password_warning
            false
        } else {
            password.helperText = null
            true
        }
    }



    private fun confirmInput(){
        if (!validateEmail() or !validatePassword()){
            return
        }

        //val fullNameText = fullName.editText?.text.toString().trim()
        val emailText = email.editText?.text.toString().trim()
        val passwordText = password.editText?.text.toString().trim()



        val loginData = LoginModel(email = emailText, password = passwordText)

        viewModel.login(loginData)

    }

    private fun separateErrors(error: String) {

        if (!emailError(error) or !passwordError(error)) {
            return
        }

        Toast.makeText(this, "Error checker didn't work", Toast.LENGTH_LONG).show()

    }

    private fun emailError(error: String): Boolean {
        val emailError = "email"

        return if (error.contains(emailError)) {
            email.helperText = "This email is incorrect. Try again."
            false
        } else {
            email.helperText = null
            true
        }
    }

    private fun passwordError(error: String): Boolean {
        val passwordError = "password"

        return if (error.contains(passwordError)) {
            password.helperText = "This password is incorrect. Try again."
            false
        } else {
            password.helperText = null
            true
        }
    }




    private fun setNav() {
        /*binding.loginButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }*/

        binding.tvSignupText.setOnClickListener{
            val intent = Intent(this, ChooseRegistrationActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }
    }
}