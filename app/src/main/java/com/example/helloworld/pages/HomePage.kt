package com.example.helloworld.pages

import android.R.attr.title
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.helloworld.AuthViewModel
import com.example.helloworld.FeedReaderDbHelper
import com.example.helloworld.Product
import com.example.helloworld.R

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel, // Ensure this is the correct type
    dbHelper: FeedReaderDbHelper
) {
    val scrollState = rememberScrollState()
    val username = authViewModel.getUsername() // Get the username
    val productList = remember {
        mutableStateListOf(*dbHelper.readData())
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
            dbHelper.insertData("Jordan Pro Strong", "Description", R.drawable.jordan_pro_strong, 199.99f)
            productList.add(Product(null,"Jordan Pro Strong", "Jordan Pro Strong",199.99f,"Description",R.drawable.jordan_pro_strong))
        }) {
            Text(text = "Insert", fontSize = 16.sp)
        }
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
