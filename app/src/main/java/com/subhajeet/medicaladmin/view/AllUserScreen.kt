package com.subhajeet.medicaladmin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medicaladmin.view.nav.Routes
import com.subhajeet.medicaladmin.viewModel.MyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllUserScreen(navController: NavController,viewModel: MyViewModel= hiltViewModel()) {

    val getAllUsersState = viewModel.getAllUsersState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllUsers()
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("User Approval Request's") },
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
        getAllUsersState.value.isLoading -> {
          //  CircularProgressIndicator(modifier = Modifier.padding(innerPadding))
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

        getAllUsersState.value.error != null -> {
            Text(text = "Error: ${getAllUsersState.value.error}")
        }

        getAllUsersState.value.success?.users?.isNotEmpty() == true -> {

            LazyColumn(modifier = Modifier.fillMaxSize(),contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 100.dp
            )
            ) {
                items(getAllUsersState.value.success?.users ?: emptyList()) {
                    eachCard(
                        name = it.name,
                        approved = it.isApproved,
                        address = it.address,
                        userId = it.user_id,
                        onClick = {
                            navController.navigate(
                                Routes.UserDetailsScreen(
                                    userId = it.user_id
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
fun eachCard(name: String,approved:Int,address:String,userId:String ,onClick:()-> Unit) {
    Card(modifier = Modifier .padding(16.dp,28.dp,16.dp,0.dp).height(140.dp).clickable {
        onClick()
    }) {



        Box(modifier = Modifier.fillMaxSize()){
            /*  Image(
                  imageVector = Icons.Default.Face,
                  contentDescription = null,
                  modifier = Modifier.fillMaxHeight().padding(18.dp).height(80.dp).width(49.dp)
              )*/
            /*AsyncImage(
                model = bookImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(115.dp).clip(CircleShape).padding(6.dp,5.dp,0.dp,0.dp)
            )*/

            Spacer(modifier=Modifier.width(10.dp))

            Text(text =name, fontSize = 20.sp, textAlign = TextAlign.Right, modifier = Modifier.padding(6.dp,0.dp,6.dp,0.dp), fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold )

            Text(
                text = if (approved == 1) "Approved" else "Not Approved",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Text(
                text = address,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Text(
                text = userId,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )


        }
    }


}


