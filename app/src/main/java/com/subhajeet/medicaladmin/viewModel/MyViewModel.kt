package com.subhajeet.medicaladmin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhajeet.medicaladmin.Utils.ResultState
import com.subhajeet.medicaladmin.models.responseModels.GetAllUsersResponse
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