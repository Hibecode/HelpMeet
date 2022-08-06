package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityEstateRegistrationBinding

class EstateRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstateRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstateRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.estateContinueButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }


    }
}