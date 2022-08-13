package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
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

class EstateRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstateRegistrationBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstateRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNav()

        val viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)






        binding.btnEstateContinue.setOnClickListener {
            var fullName = binding.etFullname.text.toString()
            var email = binding.etEmail.text.toString()
            var estateName = binding.etEstateName.text.toString()
            var estateAddress = binding.etEstateAddress.text.toString()
            var estateCountry = binding.etEstateCountry.text.toString()
            var password = binding.etPassword.text.toString()
            var confirmPassword = binding.etConfirmPassword.text.toString()

            var estateRegData = Estate(estate_address = estateAddress, estate_admin = EstateAdmin(email, password),
                estate_country = estateCountry, estate_name = estateName)

            viewModel.registerEstate(estateRegData)

            viewModel.estateRegResponse.observe(this, Observer { response ->
                if(response.isSuccessful){
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, response.message(), Toast.LENGTH_LONG).show()
                }
            })



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