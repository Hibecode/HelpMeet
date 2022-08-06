package com.example.helpmeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import com.example.helpmeet.databinding.ActivityChooseRegistrationBinding
import com.example.helpmeet.databinding.ActivityLoginBinding
import com.example.helpmeet.login.ChooseRegistrationActivity
import com.example.helpmeet.login.EstateRegistrationActivity
import com.example.helpmeet.login.ResidentRegistrationActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChooseRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.chooseResidentButton.setOnClickListener {
            val intent = Intent(this, ResidentRegistrationActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }


        binding.chooseEstateButton.setOnClickListener {
            val intent = Intent(this, EstateRegistrationActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "something")
            }
            startActivity(intent)
        }


    }
}