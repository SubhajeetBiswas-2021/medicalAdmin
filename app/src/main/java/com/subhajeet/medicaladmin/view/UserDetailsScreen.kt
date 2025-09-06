package com.subhajeet.medicaladmin.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medicaladmin.viewModel.MyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(navController: NavController,userId: String,viewModel: MyViewModel = hiltViewModel()) {

    val getUserByIdstate = viewModel.getUserByIdstate.collectAsState()

    LaunchedEffect(userId) {
        if (viewModel != null) {
            viewModel.getUserById(userId)
        }
    }

    val name = getUserByIdstate.value.success?.user?.name ?: ""
    val user_id = getUserByIdstate.value.success?.user?.user_id ?: ""
    val phone_number = getUserByIdstate.value.success?.user?.phone_number ?: ""
    val password = getUserByIdstate.value.success?.user?.password ?: ""
    var isApproved = (getUserByIdstate.value.success?.user?.isApproved ?: 0).toInt()
    val id = getUserByIdstate.value.success?.user?.id ?: ""
    val email = getUserByIdstate.value.success?.user?.email ?: ""
    val date_of_account_creation =
        getUserByIdstate.value.success?.user?.date_of_account_creation ?: ""
    var block = (getUserByIdstate.value.success?.user?.block ?: 0).toInt()
    val address = getUserByIdstate.value.success?.user?.address ?: ""
    val PinCode = getUserByIdstate.value.success?.user?.PinCode ?: ""



    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("User Approval Section") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,   // Background color
                    titleContentColor = Color.White,       //  Title color
                    navigationIconContentColor = Color.White, // ≡ Menu icon color
                    actionIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            Button(onClick = {
                if (isApproved == 0 && block == 1 || isApproved == 0 && block == 0) {
                    isApproved = 1
                    block = 0
                    viewModel.updateUser(
                        userId = userId,
                        isApproved = isApproved.toString(),
                        block = block.toString()
                    )
                } else if (isApproved == 1 && block == 0) {
                    isApproved = 0
                    block = 1
                    viewModel.updateUser(
                        userId = userId,
                        isApproved = isApproved.toString(),
                        block = block.toString()
                    )
                }

            }, modifier = Modifier) {
                if (isApproved == 0 && block == 1 || isApproved == 0 && block == 0) {
                    Text(text = "Approve User")
                } else if (isApproved == 1 && block == 0) {
                    Text(text = "Block User")
                }

            }
        }


    ) {innerPadding->


    val verticalState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(state = verticalState, enabled = true)
            .padding(innerPadding)
    ) {

        if (getUserByIdstate?.value?.isLoading == true) {
            Text("Loading...")
        } else {

            OutlinedTextField(
                value = "Name: $name",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )
            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "Email: $email",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "Address:$address",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "Phone Number=$phone_number",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "PinCode: $PinCode",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "Date Of Account Creation=$date_of_account_creation",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "User_id=$user_id",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "Password=$password",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = "Id=$id",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            OutlinedTextField(
                value = if (isApproved == 1) "Status:Approved" else "Status:Not Approved",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp, 5.dp)
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

          /*  Button(onClick = {
                if (isApproved == 0 && block == 1 || isApproved == 0 && block == 0) {
                    isApproved = 1
                    block = 0
                    viewModel.updateUser(
                        userId = userId,
                        isApproved = isApproved.toString(),
                        block = block.toString()
                    )
                } else if (isApproved == 1 && block == 0) {
                    isApproved = 0
                    block = 1
                    viewModel.updateUser(
                        userId = userId,
                        isApproved = isApproved.toString(),
                        block = block.toString()
                    )
                }

            }, modifier = Modifier) {
                if (isApproved == 0 && block == 1 || isApproved == 0 && block == 0) {
                    Text(text = "Approve User")
                } else if (isApproved == 1 && block == 0) {
                    Text(text = "Block User")
                }

            }*/
        }


    }

}



}


