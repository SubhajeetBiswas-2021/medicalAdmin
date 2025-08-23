package com.subhajeet.medicaladmin.repo

import com.subhajeet.medicaladmin.Utils.ResultState
import com.subhajeet.medicaladmin.models.ApiServices
import com.subhajeet.medicaladmin.models.responseModels.GetAllUsersResponse
import com.subhajeet.medicaladmin.models.responseModels.UpdateUserResponse
import com.subhajeet.medicaladmin.models.responseModels.getUserByIdResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repo @Inject constructor(private val apiServices: ApiServices) {


    suspend fun getAllUser(): Flow<ResultState<GetAllUsersResponse>> = flow {

        emit(ResultState.Loading)
        try {
            val response = apiServices.getAllUsers()

            if (response.isSuccessful && response.body() != null) {
                emit(ResultState.Success(response.body()!!))
            } else {
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "An error message"))
        }
    }

    suspend fun getUserById(userId: String): Flow<ResultState<getUserByIdResponse>> = flow {

        emit(ResultState.Loading)
        try {
            val response = apiServices.getUserById(userId)

            if (response.isSuccessful && response.body() != null) {
                emit(ResultState.Success(response.body()!!))
            } else {
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "An error message"))
        }
    }

    suspend fun updateUser(
        userId: String,
        isApproved: String? = null,
        name: String? = null,
        email: String? = null,
        phoneNumber: String? = null,
        address: String? = null,
        pinCode: String? = null,
        block :String?=null
    ): Flow<ResultState<UpdateUserResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiServices.updateUser(
                userId = userId,
                isApproved = isApproved,
                name = name,
                email = email,
                phoneNumber = phoneNumber,
                address = address,
                pinCode = pinCode,
                block = block
            )

            if (response.isSuccessful && response.body() != null) {
                emit(ResultState.Success(response.body()!!))
            } else {
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "An error occurred"))
        }
    }
}