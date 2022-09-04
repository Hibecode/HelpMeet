package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.util.Patterns
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
import com.example.helpmeet.utils.Resource
import java.util.regex.Pattern

class EstateRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstateRegistrationBinding
    private lateinit var viewModel: LoginViewModel

    private var email = binding.etEmail
    private var estateName = binding.etEstateName
    private var estateAddress = binding.etEstateAddress
    private var estateCountry = binding.etEstateCountry
    private var password = binding.etPassword
    private var confirmPassword = binding.etConfirmPassword


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstateRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNav()

        etEmail

        val viewModelFactory = LoginViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)


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


            var estateRegData = Estate(estateName,
                estateAddress, estateCountry, EstateAdmin(email, password))

            viewModel.registerEstate(estateRegData)








        }

        viewModel.estateRegResponse.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_LONG).show()
                    Log.d("regtest", response.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                    Log.d("errortest", response.message.toString())
                }
                is Resource.Loading -> Toast.makeText(this,"", Toast.LENGTH_LONG).show()
            }


        })






    }


    private fun validateEmail(): Boolean {
        val emailInput = email.editText?.text.toString().trim()

        return if(emailInput.isEmpty()) {
            email.error = "Field can't be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.error = "Please enter a valid email address"
            false
        } else{
            email.error = null
            true
        }

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