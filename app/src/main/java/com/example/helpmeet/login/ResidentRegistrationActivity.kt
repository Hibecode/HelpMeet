package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityResidentRegistrationBinding
import com.example.helpmeet.login.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout

class ResidentRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResidentRegistrationBinding

    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResidentRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNav()



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