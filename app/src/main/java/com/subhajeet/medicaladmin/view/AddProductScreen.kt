package com.subhajeet.medicaladmin.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.subhajeet.medicaladmin.viewModel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(viewModel: MyViewModel= hiltViewModel(),) {

    val addProductsstate = viewModel.addProductsstate.collectAsState()

    val name = remember { mutableStateOf("") }
    val stock = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(addProductsstate.value) {
        val state = addProductsstate.value
        if (state.success != null) {
            Toast.makeText(context, "Product Added successfully ✅", Toast.LENGTH_SHORT).show()
        }
        if (state.error != null) {
            Toast.makeText(context, "Failed: ${state.error}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Add Products") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,   // Background color
                    titleContentColor = Color.White,       //  Title color
                    navigationIconContentColor = Color.White, // ≡ Menu icon color
                    actionIconContentColor = Color.White
                )
            )
        }


    ) {innerPadding->

    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {


        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Name") },
            modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = stock.value,
            onValueChange = { stock.value = it },
            label = { Text(text = "Stock") },
            modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = category.value,
            onValueChange = { category.value = it },
            label = { Text(text = "Category") },
            modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = price.value,
            onValueChange = { price.value = it },
            label = { Text(text = "Price") },
            modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { /*Handle place order action*/
                when {
                    name.value.isBlank() -> {
                        Toast.makeText(
                            context,
                            "Please enter name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    stock.value.isBlank() -> {
                        Toast.makeText(
                            context,
                            "Please enter a stock",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    category.value.isBlank() -> {
                        Toast.makeText(
                            context,
                            "Please enter a category",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    price.value.isBlank() -> {
                        Toast.makeText(
                            context,
                            "Please enter a price",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        viewModel.addProducts(
                            price = price.value,
                            category = category.value,
                            stock = stock.value,
                            name = name.value,
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Product")
        }

    }
}
}