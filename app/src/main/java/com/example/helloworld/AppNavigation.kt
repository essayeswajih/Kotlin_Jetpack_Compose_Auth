package com.example.helloworld

import ForgetPasswordPage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.helloworld.pages.HomePage
import com.example.helloworld.pages.LoginPage
import com.example.helloworld.pages.RegisterPage
import com.example.helloworld.pages.SingleProductPage

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel()  // Use the correct ViewModel class here
) {
    val navController = rememberNavController()
    val isLoggedIn = authViewModel.isLoggedIn()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("register") {
            RegisterPage(modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
        composable("forgetPassword") {
            ForgetPasswordPage(modifier, navController, authViewModel)
        }
        composable(
            route = "singleProduct/{name}/{price}/{image}/{description}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("image") { type = NavType.IntType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Retrieve the arguments from the backStackEntry
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: "0.0"
            val image = backStackEntry.arguments?.getInt("image") ?: 0
            val description = backStackEntry.arguments?.getString("description") ?: ""

            SingleProductPage(name, price, image, description, modifier, navController, authViewModel)
        }
    }
}
