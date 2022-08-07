package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import com.example.helpmeet.HomeActivity
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


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