package com.subhajeet.medicaladmin.models.responseModels

data class User(
    val PinCode: String,
    val address: String,
    val block: Int,
    val date_of_account_creation: String,
    val email: String,
    val id: Int,
    val isApproved: Int,
    val name: String,
    val password: String,
    val phone_number: String,
    val user_id: String
)