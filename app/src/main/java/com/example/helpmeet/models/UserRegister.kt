package com.example.helpmeet.models

data class UserRegister(
    val email: String,
    val password: String,
    val estate_id: String,
    val house_address: String,
    val name: String,
    val estate_name: String
)