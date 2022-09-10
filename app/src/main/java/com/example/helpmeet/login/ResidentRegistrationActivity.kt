package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.text.InputFilter
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityResidentRegistrationBinding
import com.example.helpmeet.login.viewmodel.LoginViewModel
import com.example.helpmeet.login.viewmodel.LoginViewModelFactory
import com.example.helpmeet.models.Estate
import com.example.helpmeet.models.EstateAdmin
import com.example.helpmeet.models.UserRegister
import com.example.helpmeet.utils.Constants
import com.example.helpmeet.utils.Resource
import com.google.android.material.textfield.TextInputLayout

class ResidentRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResidentRegistrationBinding

    private lateinit var viewModel: LoginViewModel

    private lateinit var fullName: TextInputLayout
    private lateinit var email: TextInputLayout
    private lateinit var estateId: TextInputLayout
    private lateinit var estateName: TextInputLayout
    private lateinit var houseAddress: TextInputLayout
    private lateinit var password: TextInputLayout
    private lateinit var confirmPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResidentRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNav()

        val viewModelFactory = LoginViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        fullName = binding.etFullname
        email = binding.etEmail
        estateId = binding.etEstateId
        estateName = binding.etEstateName
        houseAddress = binding.etHouseAddress
        password = binding.etPassword
        confirmPassword = binding.etConfirmPassword



        binding.residentContinueButton.setOnClickListener {

            confirmInput()

        }

        viewModel.userRegResponse.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_LONG).show()
                    Log.d("regtest", response.data.toString())
                }
                is Resource.Error -> {
                    var errorMessage = response.message.toString()
                    separateErrors(errorMessage)
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                    Log.d("helperTexttest", response.message.toString())
                }
                is Resource.Loading -> Toast.makeText(this,"", Toast.LENGTH_LONG).show()
            }


        })



    }

    /*private fun validateFullName(): Boolean{
        val fullNameInput = fullName.editText?.text.toString().trim()

        return if(fullNameInput.isEmpty()){
            fullName.helperText = "Field can't be empty"
            false
        }
        else {
            fullName.helperText = null
            true
        }
    }*/


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

    private fun validateConfirmPassword(): Boolean{
        val confirmPasswordInput = confirmPassword.editText?.text.toString().trim()
        val passwordInput = password.editText?.text.toString().trim()

        return if( confirmPasswordInput.isEmpty()){
            confirmPassword.helperText = "Field can't be empty"
            false
        } else if (!Constants.PASSWORD_PATTERN.matcher(confirmPasswordInput).matches()){
            confirmPassword.helperText = "Please enter valid password"
            false
        } else if( confirmPasswordInput != passwordInput) {
            confirmPassword.helperText = "Password is not the same"
            false
        } else {
            confirmPassword.helperText = null
            true
        }
    }


    private fun validateEstateName(): Boolean{
        val nameInput = estateName.editText?.text.toString().trim()

        return if(nameInput.isEmpty()){
            estateName.helperText = "Field can't be empty"
            false
        }
        else {
            estateName.helperText = null
            true
        }
    }

    private fun validateHouseAddress(): Boolean{
        val addressInput = houseAddress.editText?.text.toString().trim()

        return if(addressInput.isEmpty()){
            houseAddress.helperText = "Field can't be empty"
            false
        }
        else {
            houseAddress.helperText = null
            true
        }
    }

    private fun confirmInput(){
        if (!validateEstateName() or !validateHouseAddress() /*or !validateFullName()*/ or !validateEmail() or !validatePassword() or !validateConfirmPassword()){
            return
        }

        var fullNameText = fullName.editText?.text.toString().trim()
        val emailText = email.editText?.text.toString().trim()
        val estateIdText = estateId.editText?.text.toString().trim()
        val estateNameText = estateName.editText?.text.toString().trim()
        val houseAddressText = houseAddress.editText?.text.toString().trim()
        val passwordText = password.editText?.text.toString().trim()



        val estateRegData = UserRegister()

        viewModel.registerEstate(estateRegData)

    }

    private fun separateErrors(error: String) {

        if (!countryCodeError(error) or !emailError(error) or !houseAddressError(error) or !estateNameError(error)) {
            return
        }

        Toast.makeText(this, "Error checker didn't work", Toast.LENGTH_LONG).show()

    }

    private fun emailError(error: String): Boolean {
        val emailError = "user with this email address already exists."

        return if (error.contains(emailError)) {
            email.helperText = emailError.capitaliseFirstLetter()
            false
        } else {
            email.helperText = null
            true
        }
    }

    private fun estateNameError(error: String): Boolean {
        val estateNameError = "estate with this estate name already exists."

        return if (error.contains(estateNameError)) {
            estateName.helperText = estateNameError.capitaliseFirstLetter()
            false
        } else {
            estateName.helperText = null
            true
        }
    }

    private fun houseAddressError(error: String): Boolean {
        val houseAddressError = "estate with this estate address already exists."

        return if (error.contains(houseAddressError)) {
            houseAddress.helperText = houseAddressError.capitaliseFirstLetter()
            false
        } else {
            houseAddress.helperText = null
            true
        }
    }

    private fun countryCodeError(error: String): Boolean {
        val countryCodeError = "estate_country"

        return if (error.contains(countryCodeError)) {
            estateId.helperText = "Please enter a valid country code"
            false
        } else {
            estateId.helperText = null
            true
        }
    }


    private fun String.capitaliseFirstLetter(): String {
        return this.replaceFirst(this[0], this[0].uppercaseChar())
    }



    private fun setNav() {
        binding.residentContinueButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }

        binding.tvLoginText.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }
    }
}