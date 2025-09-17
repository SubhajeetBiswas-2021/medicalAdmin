package com.subhajeet.medicaladmin.view.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.subhajeet.medicaladmin.view.AddProductScreen
import com.subhajeet.medicaladmin.view.AllOrdersScreen
import com.subhajeet.medicaladmin.view.AllUserScreen
import com.subhajeet.medicaladmin.view.OrderDetailsScreen
import com.subhajeet.medicaladmin.view.UserDetailsScreen
import com.subhajeet.medicaladmin.viewModel.MyViewModel

@Composable
fun NavApp(viewModels : MyViewModel) {

    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem("All Users Approval Requests", icon = Icons.Default.Person),
        BottomNavItem("Add Products", icon = Icons.Default.Add),
        BottomNavItem("AllOrders",icon = Icons.Default.ShoppingCart),
        //  BottomNavItem("Settings",icon = Icons.Default.Settings)
    )
    var selected by remember { mutableIntStateOf(0) } // managing state for for which bottomNavItems is selected


    // ✅ Observe the current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    // ✅ Define screens where bottom nav should be hidden
    val hideBottomBarRoutes = listOf(
        Routes.UserDetailsScreen::class.qualifiedName,
        Routes.OrderDetailsScreen::class.qualifiedName,
      //  Routes.WaitingRoutes::class.qualifiedName
    )

    val shouldHideBottomBar = hideBottomBarRoutes.any { route ->
        currentDestination?.startsWith(route ?: "") == true
    }

    Scaffold(
        bottomBar = {

            if (!shouldHideBottomBar){
            NavigationBar {

                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        alwaysShowLabel = true,
                        selected = selected == index,
                        onClick = {

                            selected = index  //updating the state
                            when (index) {
                                0 -> navController.navigate(Routes.AllUserScreen)
                                1 -> navController.navigate(Routes.AddProductScreen)
                                2-> navController.navigate(Routes.AllOrdersScreen)

                            }

                        },
                        icon = {
                            Icon(
                                imageVector = item.icon, contentDescription = item.name
                            )
                        }
                    )
                }

            }
        }

        }
    ) {innerPadding->

    NavHost(navController, startDestination = Routes.AllUserScreen) {

        composable<Routes.AllUserScreen> {
            AllUserScreen(navController)
        }

        composable<Routes.UserDetailsScreen> {

            val data = it.toRoute<Routes.UserDetailsScreen>()
            UserDetailsScreen(
                navController,
                userId = data.userId,

                )
        }

        composable<Routes.AddProductScreen> {
            AddProductScreen()
        }

        composable<Routes.AllOrdersScreen> {
            AllOrdersScreen(navController)
        }

        composable<Routes.OrderDetailsScreen> {
            val data = it.toRoute<Routes.OrderDetailsScreen>()
            OrderDetailsScreen(
                navController,
                orderId = data.orderId
            )
        }

    }

}


}

data class BottomNavItem(
    val name: String,
    val icon: ImageVector
)