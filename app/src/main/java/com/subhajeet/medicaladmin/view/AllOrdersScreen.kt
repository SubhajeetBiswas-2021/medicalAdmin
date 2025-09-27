package com.subhajeet.medicaladmin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medicaladmin.view.nav.Routes
import com.subhajeet.medicaladmin.viewModel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllOrdersScreen(navController: NavController,viewModel:MyViewModel= hiltViewModel()) {

    val getAllOrdersState = viewModel.getAllOrdersState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllOrders()
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("User's Order Approval Request's") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,   // Background color
                    titleContentColor = Color.White,       //  Title color
                    navigationIconContentColor = Color.White, // ≡ Menu icon color
                    actionIconContentColor = Color.White
                )
            )
        }


    ) {innerPadding->

    when {
        getAllOrdersState.value.isLoading -> {
           // CircularProgressIndicator(modifier = Modifier.padding(innerPadding))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000)), // semi-transparent overlay
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    strokeWidth = 5.dp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Loading ...",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }

        getAllOrdersState.value.error != null -> {
            Text(text = "Error: ${getAllOrdersState.value.error}")
        }

        getAllOrdersState.value.success?.orders?.isNotEmpty() == true -> {

            LazyColumn(modifier = Modifier.fillMaxSize(),contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 100.dp)) {
                items(getAllOrdersState.value.success?.orders ?: emptyList()) {
                    OrdersCard(
                        productName = it.productName,
                        Quantity = it.Quantity,
                        isApproved = it.isApproved.toString(),
                        UserId = it.user_id,
                        OrderId = it.order_id,
                        Date = it.date_of_order_creation,
                        Message = it.message,
                        onClick = {
                            navController.navigate(
                                Routes.OrderDetailsScreen(
                                    orderId = it.order_id
                                )
                            )
                        }
                    )


                }

            }

        }
    }
}

}


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrdersCard(productName:String,isApproved:String,UserId:String,OrderId:String,Date:String,Message:String,Quantity:String,onClick:()-> Unit) {

    Card(modifier = Modifier.padding(8.dp).height(180.dp).clickable {
        onClick()
    }, colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    )) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,)
        {
            Box(modifier = Modifier.fillMaxWidth().height(120.dp) ) {

                Text(
                    text = "Product:${productName}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.padding(6.dp, 0.dp, 6.dp, 0.dp),
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text =  "${if (isApproved == "1") "Approved" else "Not Approved"}/Quantity:$Quantity",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 12.dp, bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "UserId:${UserId}",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp, bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "OdrId:${OrderId}",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 12.dp, bottom = 8.dp)
                )




            }
            Text(
                text = "Date:${Date}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.Start)

                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Text(
                text = "message:${Message}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.End)

                    .padding(end = 12.dp, bottom = 8.dp)
            )
        }
    }

}