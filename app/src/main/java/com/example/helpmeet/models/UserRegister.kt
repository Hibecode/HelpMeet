package com.example.helpmeet.models

data class UserRegister(
    val name: String,
    val email: String,
    val estate_id: String,
    val estate_name: String,
    val house_address: String,
    val password: String
)