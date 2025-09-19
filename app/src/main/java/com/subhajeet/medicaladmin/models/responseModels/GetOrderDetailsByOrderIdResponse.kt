package com.subhajeet.medicaladmin.models.responseModels

data class GetOrderDetailsByOrderIdResponse(
    val orderDetails: List<OrderDetailX>,
    val status: Int
)