package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.text.InputFilter
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityEstateRegistrationBinding
import com.example.helpmeet.login.viewmodel.LoginViewModel
import com.example.helpmeet.login.viewmodel.LoginViewModelFactory
import com.example.helpmeet.models.Estate
import com.example.helpmeet.models.EstateAdmin
import com.example.helpmeet.utils.Constants.Companion.PASSWORD_PATTERN
import com.example.helpmeet.utils.Constants.Companion.password_warning
import com.example.helpmeet.utils.Resource
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class EstateRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstateRegistrationBinding
    private lateinit var viewModel: LoginViewModel

    private lateinit var email: TextInputLayout
    private lateinit var estateName: TextInputLayout
    private lateinit var estateAddress: TextInputLayout
    private lateinit var estateCountry: TextInputLayout
    private lateinit var password: TextInputLayout
    private lateinit var confirmPassword: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstateRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNav()


        val viewModelFactory = LoginViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        email = binding.etEmail
        estateName = binding.etEstateName
        estateAddress = binding.etEstateAddress
        estateCountry = binding.etEstateCountry
        password = binding.etPassword
        confirmPassword = binding.etConfirmPassword


        estateCountry.editText?.filters = estateCountry.editText?.filters?.plus(InputFilter.AllCaps())


        /*var email = binding.etEmail.editText.toString().trim()
        var estateName = binding.etEstateName.editText.toString().trim()
        var estateAddress = binding.etEstateAddress.editText.toString().trim()
        var estateCountry = binding.etEstateCountry.editText.toString().trim()
        var password = binding.etPassword.editText.toString().trim()
        var confirmPassword = binding.etConfirmPassword.editText.toString().trim()*/

        /*if(email.isEmpty()){
            Patterns.EMAIL_ADDRESS
        }*/



        binding.btnEstateContinue.setOnClickListener {


            /*var estateRegData = Estate(estateName,
                estateAddress, estateCountry, EstateAdmin(email, password))

            viewModel.registerEstate(estateRegData)
*/
            confirmInput()






        }

        viewModel.estateRegResponse.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_LONG).show()
                    Log.d("regtest", response.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                    Log.d("helperTexttest", response.message.toString())
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
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            password.helperText = password_warning
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
        } else if (!PASSWORD_PATTERN.matcher(confirmPasswordInput).matches()){
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

    private fun validateCountryCode(): Boolean{
        val countryCodeInput = estateCountry.editText?.text.toString().trim()

        return if(countryCodeInput.isEmpty()){
            estateCountry.helperText = "Field can't be empty"
            false
        } else if(!countryCodeInput.isAllUpperCase() or (countryCodeInput.length != 2)) {
            estateCountry.helperText = "Please enter a valid Country Code"
            false
        } else {
            estateCountry.helperText = null
            true
        }
    }

    private fun String.isAllUpperCase(): Boolean{
         for (i in this){
             if (i.isLowerCase()) return false
         }
        return true
    }

    private fun validateEstateName(): Boolean{
        val countryCodeInput = estateCountry.editText?.text.toString().trim()

        return if(countryCodeInput.isEmpty()){
            estateCountry.helperText = "Field can't be empty"
            false
        }
        else {
            estateCountry.helperText = null
            true
        }
    }

    private fun validateEstateAddress(): Boolean{
        val countryCodeInput = estateCountry.editText?.text.toString().trim()

        return if(countryCodeInput.isEmpty()){
            estateCountry.helperText = "Field can't be empty"
            false
        }
        else {
            estateCountry.helperText = null
            true
        }
    }

    fun confirmInput(){
        if (!validateCountryCode() or !validateEmail() or !validatePassword() or !validateConfirmPassword()){
            return
        }

        Toast.makeText(this, "Didn't work", Toast.LENGTH_LONG).show()

    }





    // Set Navigation for the Continue and Login text buttons
    fun setNav() {
        /*binding.btnEstateContinue.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }*/

        binding.tvLoginText.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }
    }


}