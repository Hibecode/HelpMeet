package com.example.helpmeet.models

data class SavedEstateDetails(
    val estate_name: String,
    val estate_address: String,
    val estate_country: String,
    val estate_id: String,
    val public_id: String,
    val member: Member
)