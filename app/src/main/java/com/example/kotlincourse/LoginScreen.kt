package com.example.kotlincourse

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlincourse.data.viewmodel.AuthViewModel
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginResult by viewModel.loginResult.collectAsState()

    // React on login success to navigate
    LaunchedEffect(loginResult) {
        loginResult?.fold(
            onSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }  // Adjust "login" route name accordingly
                }
            },
            onFailure = {
                // Optionally show error via Snackbar or Toast
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBBDEFB))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Login Image",
            modifier = Modifier.size(200.dp)
        )

        Text(
            text = "Welcome back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Login to your account")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth(0.8f),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.8f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.login(userId.trim(), password.trim())
            },
            modifier = Modifier.size(width = 220.dp, height = 56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        loginResult?.let { result ->
            result.fold(
                onSuccess = { token ->
                    Text(
                        text = "Login Success! Token: $token",
                        color = Color(0xFF2E7D32),
                        modifier = Modifier.padding(16.dp)
                    )
                },
                onFailure = { error ->
                    Text(
                        text = error.message ?: "Login Failed",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            "Forgot Password?",
            modifier = Modifier.clickable { /* TODO: Add forgot password logic */ },
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "or sign in with")
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Google",
            modifier = Modifier
                .size(80.dp)
                .clickable { /* TODO: Add Google Sign-in logic */ }
        )
    }
}
