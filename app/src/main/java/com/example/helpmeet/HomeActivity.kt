package com.example.helpmeet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helpmeet.databinding.ActivityHomeBinding
import com.example.helpmeet.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}