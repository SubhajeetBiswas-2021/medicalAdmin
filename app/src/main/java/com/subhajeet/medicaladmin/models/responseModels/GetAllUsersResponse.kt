package com.subhajeet.medicaladmin.models.responseModels

data class GetAllUsersResponse(
    val status: Int,
    val users: List<User>
)