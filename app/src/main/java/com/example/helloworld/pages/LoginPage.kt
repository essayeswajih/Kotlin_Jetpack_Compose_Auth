package com.example.helloworld.pages

import android.graphics.ColorSpace.Rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.helloworld.R

@Composable
fun LoginPage (
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: ViewModel
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column (modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Login", fontSize = 32.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(126, 87, 194),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.mobile_login_bro),
            contentDescription = "Login Background",
            contentScale = ContentScale.Fit
        )

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email")}
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = {
                navController.navigate("forgetPassword")
            }
        ) {
            Text(text = "Forgot Password?")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Handle login logic, perhaps using the authViewModel
            }
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = {
                navController.navigate("register")
            }
        ) {
            Text(text = "I don't have an account!") // Register button navigation()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}