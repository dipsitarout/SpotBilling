package com.example.kotlincourse

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New token: $token")
        // TODO: Send token to your server if required
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM", "From: ${remoteMessage.from}")
        remoteMessage.notification?.let {
            Log.d("FCM", "Notification Body: ${it.body}")
            showNotification(it.title, it.body)
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showNotification(title: String?, body: String?) {
        NotificationHelper.showNotification(applicationContext, title, body)
    }
}