package com.subhajeet.medicaladmin.view.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.subhajeet.medicaladmin.view.AllUserScreen
import com.subhajeet.medicaladmin.view.UserDetailsScreen
import com.subhajeet.medicaladmin.viewModel.MyViewModel

@Composable
fun NavApp(viewModels : MyViewModel) {

    val navController = rememberNavController()

    NavHost(navController , startDestination = Routes.AllUserScreen ){

        composable<Routes.AllUserScreen>{
            AllUserScreen(navController)
        }

        composable<Routes.UserDetailsScreen>{

            val data = it.toRoute<Routes.UserDetailsScreen>()
            UserDetailsScreen(
                navController,
                userId = data.userId,

            )
        }

    }


}