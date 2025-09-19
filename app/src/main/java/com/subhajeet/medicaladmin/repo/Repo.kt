package com.subhajeet.medicaladmin.repo

import com.subhajeet.medicaladmin.Utils.ResultState
import com.subhajeet.medicaladmin.models.ApiServices
import com.subhajeet.medicaladmin.models.responseModels.AddProductResponse
import com.subhajeet.medicaladmin.models.responseModels.GetAllOrdersResponse
import com.subhajeet.medicaladmin.models.responseModels.GetAllUsersResponse
import com.subhajeet.medicaladmin.models.responseModels.GetOrderDetailsByOrderIdResponse
import com.subhajeet.medicaladmin.models.responseModels.UpdateOrderResponse
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


    suspend fun addProducts(price:String,category:String,stock:String,name:String):Flow<ResultState<AddProductResponse>> = flow {

        emit(ResultState.Loading)
        try {
            val response = apiServices.addProducts(price,category,stock,name)

            if(response.isSuccessful && response.body() != null){
                emit(ResultState.Success(response.body()!!))
            }else{
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        }catch (e:Exception){
            emit(ResultState.Error(e.message?:"An error message"))
        }
    }


    suspend fun getAllOrders(): Flow<ResultState<GetAllOrdersResponse>> = flow {

        emit(ResultState.Loading)
        try {
            val response = apiServices.getAllOrders()

            if (response.isSuccessful && response.body() != null) {
                emit(ResultState.Success(response.body()!!))
            } else {
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "An error message"))
        }
    }



    suspend fun getOrderDetailByOrderId(order_id: String): Flow<ResultState<GetOrderDetailsByOrderIdResponse>> = flow {

        emit(ResultState.Loading)
        try {
            val response = apiServices.getOrderDetailByOrderId(order_id)

            if (response.isSuccessful && response.body() != null) {
                emit(ResultState.Success(response.body()!!))
            } else {
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "An error message"))
        }
    }

    suspend fun updateOrderDetails(
        orderId: String,
        Quantity: String? = null,
        price: String? = null,
        productName: String? = null,
        message: String? = null,
        category: String? = null,
        product_id :String?=null,
        user_id:String?=null,
        isApproved:String?=null
    ): Flow<ResultState<UpdateOrderResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiServices.updateOrderDetails(
                orderId = orderId,
                Quantity = Quantity,
                price = price,
                productName = productName,
                message = message,
                category = category,
                product_id = product_id,
                user_id = user_id,
                isApproved=isApproved
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