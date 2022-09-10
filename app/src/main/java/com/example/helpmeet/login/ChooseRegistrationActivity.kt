package com.example.helpmeet.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import com.example.helpmeet.R
import com.example.helpmeet.databinding.ActivityChooseRegistrationBinding

class ChooseRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.chooseResidentButton.setOnClickListener {
            val intent = Intent(this, ResidentRegistrationActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }

        binding.chooseEstateButton.setOnClickListener{
            val intent = Intent(this, EstateRegistrationActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }

    }
}