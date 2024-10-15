package com.example.helloworld

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworld.pages.HomePage
import com.example.helloworld.pages.LoginPage
import com.example.helloworld.pages.RegisterPage

@Composable
fun AppNavigation(modifier: Modifier = Modifier,authViewModel: ViewModel = AuthViewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, modifier = modifier, startDestination = "login", builder = {
        composable("login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("register") {
            RegisterPage(modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
    })
}