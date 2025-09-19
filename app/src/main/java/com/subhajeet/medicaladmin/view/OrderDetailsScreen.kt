package com.subhajeet.medicaladmin.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun OrderDetailsScreen(navController: NavController,viewModel: MyViewModel = hiltViewModel(), orderId: String) {

    val getOrderDetailByOrderIdState = viewModel.getOrderDetailByOrderIdState.collectAsState()

    LaunchedEffect(orderId) {
        if (viewModel != null) {
            viewModel.getOrderDetailByOrderId(orderId)
        }
    }

               //This procudure dont work as List<Order>
   /* val quantity = getOrderDetailByOrderIdState.value.success?.orderDetails?.Quantity ?: ""
    val id = getOrderDetailByOrderIdState.value.success?.orderDetails?.id ?: ""
    val date =
        getOrderDetailByOrderIdState.value.success?.orderDetails?.date_of_order_creation ?: ""
    val userId = getOrderDetailByOrderIdState.value.success?.orderDetails?.user_id ?: ""
    var isApproved = getOrderDetailByOrderIdState.value.success?.orderDetails?.isApproved ?: ""
    val price = getOrderDetailByOrderIdState.value.success?.orderDetails?.price ?: ""
    val category = getOrderDetailByOrderIdState.value.success?.orderDetails?.category ?: ""
    val message = getOrderDetailByOrderIdState.value.success?.orderDetails?.message ?: ""
    val productName = getOrderDetailByOrderIdState.value.success?.orderDetails?.productName ?: ""
    val productId = getOrderDetailByOrderIdState.value.success?.orderDetails?.product_id ?: ""*/



    val detail = getOrderDetailByOrderIdState.value.success?.orderDetails?.firstOrNull()
    var isApproved = detail?.isApproved ?: 0

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Order Approval Section") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,   // Background color
                    titleContentColor = Color.White,       //  Title color
                    navigationIconContentColor = Color.White, // ≡ Menu icon color
                    actionIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            detail?.let {
                val nextStatus = if (isApproved == 0) 1 else 0
                val buttonText = if (isApproved == 0) "Approve Order" else "Block Order"

                Button(onClick = {
                    isApproved = nextStatus
                    viewModel.updateOrderDetails(
                        orderId = orderId,
                        isApproved = nextStatus.toString()
                    )
                }) {
                    Text(text = buttonText)
                }
            }
        }


    ) { innerPadding ->


        //val verticalState = rememberScrollState()

        val orderDetails = getOrderDetailByOrderIdState.value.success?.orderDetails ?: emptyList()


        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(orderDetails) { detail ->

                if (getOrderDetailByOrderIdState?.value?.isLoading == true) {
                    Text("Loading...")
                } else {

                    OutlinedTextField(
                        value = "Product Name: ${detail.productName}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "Quantity: ${detail.Quantity}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "Category:${detail.category}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "Message=${detail.message}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "ProductId: ${detail.product_id}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "Date Of Order Placed=${detail.date_of_order_creation}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "User_id=${detail.user_id}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "Price=${detail.price}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = "Id=${detail.id}",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    OutlinedTextField(
                        value = if (detail.isApproved == 1) "Status:Approved" else "Status:Not Approved",
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp, 5.dp, 5.dp)
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))

                    Spacer(modifier = Modifier.fillMaxWidth().padding(0.dp, 34.dp, 0.dp, 0.dp))


                }

            }


        }
    }

}
