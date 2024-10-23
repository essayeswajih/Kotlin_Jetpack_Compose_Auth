package com.example.helloworld

import ForgetPasswordPage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworld.pages.HomePage
import com.example.helloworld.pages.LoginPage
import com.example.helloworld.pages.RegisterPage

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
    }
}
