package com.subhajeet.medicaladmin.models.responseModels

data class GetAllOrdersResponse(
    val orders: List<Order>,
    val status: Int
)