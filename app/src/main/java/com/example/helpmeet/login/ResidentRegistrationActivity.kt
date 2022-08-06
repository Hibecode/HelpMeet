package com.example.helpmeet.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityResidentRegistrationBinding

class ResidentRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResidentRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResidentRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}