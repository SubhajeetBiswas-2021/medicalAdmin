package com.subhajeet.medicaladmin.models.responseModels

data class getUserByIdResponse(
    val status: Int,
    val user: UserX
)