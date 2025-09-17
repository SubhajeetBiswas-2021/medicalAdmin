package com.subhajeet.medicaladmin.models.responseModels

data class OrderDetailX(
    val Quantity: String,
    val category: String,
    val date_of_order_creation: String,
    val id: Int,
    val isApproved: Int,
    val message: String,
    val order_id: String,
    val price: String,
    val productName: String,
    val product_id: String,
    val user_id: String
)