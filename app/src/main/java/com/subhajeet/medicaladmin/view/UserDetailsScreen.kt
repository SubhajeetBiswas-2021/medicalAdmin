package com.subhajeet.medicaladmin.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medicaladmin.viewModel.MyViewModel


@Composable
fun UserDetailsScreen(navController: NavController,userId: String,viewModel: MyViewModel = hiltViewModel()) {

    val getUserByIdstate = viewModel.getUserByIdstate.collectAsState()

    LaunchedEffect(userId) {
        if (viewModel != null) {
            viewModel.getUserById(userId)
        }
    }

    val name = getUserByIdstate.value.success?.user?.name ?:""
    val user_id = getUserByIdstate.value.success?.user?.user_id ?:""
    val phone_number = getUserByIdstate.value.success?.user?.phone_number ?:""
    val password = getUserByIdstate.value.success?.user?.password ?:""
    var isApproved = (getUserByIdstate.value.success?.user?.isApproved ?:0).toInt()
    val id = getUserByIdstate.value.success?.user?.id ?:""
    val email = getUserByIdstate.value.success?.user?.email ?:""
    val date_of_account_creation = getUserByIdstate.value.success?.user?.date_of_account_creation ?:""
    var block = (getUserByIdstate.value.success?.user?.block ?:0).toInt()
    val address = getUserByIdstate.value.success?.user?.address ?:""
    val PinCode = getUserByIdstate.value.success?.user?.PinCode ?:""




    val verticalState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().verticalScroll(state = verticalState, enabled = true).padding(35.dp)) {

        if(getUserByIdstate?.value?.isLoading == true){
            Text("Loading...")
        }else{

            Text(text="PinCode: $PinCode", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="Address:$address", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text=if(block==0) "Not Blocked" else "Blocked", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="Date Of Account Creation=$date_of_account_creation", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="Email=$email", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="Id=${id.toString()}", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text=if (isApproved == 1) "Approved" else "Not Approved", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="Name:$name", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="Password=$password", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="Phone Number=$phone_number", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Text(text="User_id=$user_id", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp,34.dp,0.dp,0.dp))

            Button(onClick = {
                if(isApproved == 0 && block == 1 || isApproved==0 && block ==0){
                    isApproved = 1
                    block = 0
                   viewModel.updateUser(userId=userId,isApproved= isApproved.toString(), block=block.toString())
                }else if(isApproved==1 && block==0){
                    isApproved = 0
                    block=1
                    viewModel.updateUser(userId=userId, isApproved=isApproved.toString(),block= block.toString())
                }

            }, modifier = Modifier) {
                if(isApproved == 0 && block == 1 || isApproved==0 && block ==0){
                    Text(text="Approve User")
                }else if (isApproved==1 && block==0){
                    Text(text="Block User")
                }

            }
        }



    }



}


