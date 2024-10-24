package com.example.helloworld.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.helloworld.AuthViewModel
import com.example.helloworld.Product
import com.example.helloworld.R

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel // Ensure this is the correct type
) {
    val scrollState = rememberScrollState()
    val username = authViewModel.getUsername() // Get the username
    val productList = remember {
        mutableStateListOf(
            Product("jordan pro strong", 99.99,"Description 1", R.drawable.jordan_pro_strong),
            Product("air jordan retro 6", 99.99,"Description 2", R.drawable.air_jordan_retro_6),
            Product("jordan_mvp", 99.99,"Description 3", R.drawable.jordan_mvp),
            Product("jordan_tatum", 99.99,"Description 4", R.drawable.jordan_tatum),
            Product("Product 5", 99.99,"Description 5", R.drawable.ic_launcher_foreground),
            Product("Product 6", 99.99,"Description 6", R.drawable.ic_launcher_foreground),
            Product("Product 7", 99.99,"Description 7", R.drawable.ic_launcher_foreground),
            Product("Product 8", 99.99,"Description 8", R.drawable.ic_launcher_foreground),
            Product("Product 9", 99.99,"Description 9", R.drawable.ic_launcher_foreground),
            Product("Product 10", 99.99,"Description 10", R.drawable.ic_launcher_foreground),
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Page", fontSize = 32.sp)
        Text(text = "Welcome, $username!", fontSize = 24.sp) // Display the username

        TextButton(onClick = {
            authViewModel.logout()
            navController.navigate("login")
        }) {
            Text(text = "Logout", fontSize = 16.sp)
        }
        LazyColumn (modifier = Modifier.fillMaxSize()) {
            items(productList) { product ->
                ProductCard(product = product, navController = navController)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product,navController: NavHostController,) {
    Card(
        modifier = Modifier.fillMaxSize().padding(
            horizontal = 16.dp,
            vertical = 8.dp,

        ),
        onClick = {
            // Handle product click
            // For example, you can navigate to the product details page

            navController.navigate("singleProduct/${product.name}/${product.price}/${product.image}/${product.description}")

        }

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name)
            Text(text = product.price.toString())
            Image(painter = painterResource(id = product.image), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.fillMaxSize())
        }
    }

}
