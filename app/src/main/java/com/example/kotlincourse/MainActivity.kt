package com.example.kotlincourse

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.*
import com.example.kotlincourse.ui.OtpScreen
import com.example.kotlincourse.ui.screens.HomeScreen
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Ask notification permission if needed (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // Step 2: Subscribe to topic
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TOPIC", "✅ Subscribed to topic: allDevices")
                } else {
                    Log.e("TOPIC", "❌ Failed to subscribe", task.exception)
                }
            }

        // Step 3: Get FCM token
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM_TOKEN", "Token: $token")
            } else {
                Log.e("FCM_TOKEN", "Fetching FCM token failed", task.exception)
            }
        }

        setContent {
            AppNavigator()
        }
    }

    // Permission launcher
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("PERMISSION", "✅ Notification permission granted")
        } else {
            Log.w("PERMISSION", "❌ Notification permission denied")
        }
    }

    @Composable
    fun AppNavigator() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController)
            }
            composable("Login") {
                OtpScreen(navController)
            }
            composable("Login2") {
                LoginScreen(navController)
            }
            composable("UserDashboard") {
                UserDashboardScreen(navController)
            }
            composable("home") {
                HomeScreen(navController = navController)
            }
        }
    }
}
