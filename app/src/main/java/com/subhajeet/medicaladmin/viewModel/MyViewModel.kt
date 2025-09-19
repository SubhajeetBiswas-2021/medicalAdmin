package com.subhajeet.medicaladmin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhajeet.medicaladmin.Utils.ResultState
import com.subhajeet.medicaladmin.models.responseModels.AddProductResponse
import com.subhajeet.medicaladmin.models.responseModels.GetAllOrdersResponse
import com.subhajeet.medicaladmin.models.responseModels.GetAllUsersResponse
import com.subhajeet.medicaladmin.models.responseModels.GetOrderDetailsByOrderIdResponse
import com.subhajeet.medicaladmin.models.responseModels.UpdateOrderResponse
import com.subhajeet.medicaladmin.models.responseModels.UpdateUserResponse
import com.subhajeet.medicaladmin.models.responseModels.getUserByIdResponse
import com.subhajeet.medicaladmin.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repo): ViewModel() {


    private val _getAllUsersState = MutableStateFlow<GetAllUsersState>(GetAllUsersState())
    val getAllUsersState = _getAllUsersState.asStateFlow()


    private val _getUserByIdstate = MutableStateFlow(getUserByIdState())
    val getUserByIdstate = _getUserByIdstate.asStateFlow()

    private val _updateUserState = MutableStateFlow(UpdateUserState())
    val updateUserState = _updateUserState.asStateFlow()

    private val _addProductsstate = MutableStateFlow(AddProductsState())
    val addProductsstate =  _addProductsstate.asStateFlow()

    private val _getAllOrdersState = MutableStateFlow<GetAllOrdersState>(GetAllOrdersState())
    val getAllOrdersState = _getAllOrdersState.asStateFlow()

    private val _getOrderDetailByOrderIdState = MutableStateFlow(GetOrderDetailByOrderIdState())
    val getOrderDetailByOrderIdState = _getOrderDetailByOrderIdState.asStateFlow()

    private val _updateOrderState = MutableStateFlow(UpdateOrdersState())
    val updateOrderState = _updateOrderState.asStateFlow()

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllUser().collect { result ->
                println("Result: $result") // Add this line
                when (result) {
                    is ResultState.Loading -> {
                        _getAllUsersState.value = GetAllUsersState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getAllUsersState.value = GetAllUsersState(success = result.data)
                    }

                    is ResultState.Error -> {
                        _getAllUsersState.value = GetAllUsersState(error = result.message)
                    }
                }

            }
        }
    }


    fun getUserById(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getUserById(userId).collect { result ->
                println("Result: $result") // Add this line
                when (result) {
                    is ResultState.Loading -> {
                        _getUserByIdstate.value = getUserByIdState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getUserByIdstate.value = getUserByIdState(success = result.data)
                    }

                    is ResultState.Error -> {
                        _getUserByIdstate.value = getUserByIdState(error = result.message)
                    }
                }

            }
        }
    }

    fun addProducts(price:String,category:String,stock:String,name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addProducts(price,category,stock,name).collect{ result->

                when (result) {
                    is ResultState.Loading -> {
                        _addProductsstate.value = AddProductsState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addProductsstate.value = AddProductsState(success = result.data)
                    }

                    is ResultState.Error -> {
                        _addProductsstate.value = AddProductsState(error = result.message)
                    }
                }

            }
        }
    }

    fun updateUser(
        userId: String,
        isApproved: String? = null,
        name: String? = null,
        email: String? = null,
        phoneNumber: String? = null,
        address: String? = null,
        pinCode: String? = null,
        block:String?=null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateUser(
                userId = userId,
                isApproved = isApproved,
                name = name,
                email = email,
                phoneNumber = phoneNumber,
                address = address,
                pinCode = pinCode,
                block = block
            ).collect { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _updateUserState.value = UpdateUserState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _updateUserState.value = UpdateUserState(success = result.data)
                    }

                    is ResultState.Error -> {
                        _updateUserState.value = UpdateUserState(error = result.message)
                    }
                }
            }
        }


    }

    fun getAllOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllOrders().collect { result ->
                println("Result: $result") // Add this line
                when (result) {
                    is ResultState.Loading -> {
                        _getAllOrdersState.value = GetAllOrdersState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getAllOrdersState.value = GetAllOrdersState(success = result.data)
                    }

                    is ResultState.Error -> {
                        _getAllOrdersState.value = GetAllOrdersState(error = result.message)
                    }
                }

            }
        }
    }

    fun getOrderDetailByOrderId(order_id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getOrderDetailByOrderId(order_id).collect { result ->
                println("Result: $result") // Add this line
                when (result) {
                    is ResultState.Loading -> {
                        _getOrderDetailByOrderIdState.value = GetOrderDetailByOrderIdState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getOrderDetailByOrderIdState.value = GetOrderDetailByOrderIdState(success = result.data)
                    }

                    is ResultState.Error -> {
                        _getOrderDetailByOrderIdState.value = GetOrderDetailByOrderIdState(error = result.message)
                    }
                }

            }
        }
    }

    fun updateOrderDetails(
        orderId: String,
        Quantity: String? = null,
        price: String? = null,
        productName: String? = null,
        message: String? = null,
        category: String? = null,
        product_id :String?=null,
        user_id:String?=null,
        isApproved:String?=null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateOrderDetails(
                orderId = orderId,
                Quantity = Quantity,
                price = price,
                productName = productName,
                message = message,
                category = category,
                product_id = product_id,
                user_id = user_id,
                isApproved=isApproved
            ).collect { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _updateOrderState.value = UpdateOrdersState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _updateOrderState.value = UpdateOrdersState(success = result.data)
                    }

                    is ResultState.Error -> {
                        _updateOrderState.value = UpdateOrdersState(error = result.message)
                    }
                }
            }
        }


    }
}

data class GetAllUsersState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:GetAllUsersResponse? = null
)

data class  getUserByIdState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success: getUserByIdResponse? = null
)

data class UpdateUserState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success: UpdateUserResponse? = null
)

data class AddProductsState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:AddProductResponse? = null
)

data class GetAllOrdersState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:GetAllOrdersResponse? = null
)

data class  GetOrderDetailByOrderIdState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success: GetOrderDetailsByOrderIdResponse? = null
)

data class UpdateOrdersState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success: UpdateOrderResponse? = null
)