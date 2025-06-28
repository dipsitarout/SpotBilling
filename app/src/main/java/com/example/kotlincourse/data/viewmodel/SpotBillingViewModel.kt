package com.example.kotlincourse.data.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.kotlincourse.data.local.AppDatabase
import com.example.kotlincourse.data.repository.SpotBillingRepository
import kotlinx.coroutines.launch

class DownloadViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "my_database"
    ).build()

    private val userDao = db.loginResponseDao()
    private val repository = SpotBillingRepository()

    fun getUserAndDownload() {
        viewModelScope.launch {
            try {
                // Use suspend function to get user from DB
                val user = userDao.getLoginResponse()
                if (user != null) {
                    // API call using token and user_id from DB
                    val response = repository.fetchUserData(user.token, user.user_id)
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(getApplication(), "Success ✅", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(getApplication(), "API Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(getApplication(), "No user found in DB", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(getApplication(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
