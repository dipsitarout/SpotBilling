package com.example.kotlincourse.data.model

data class LoginRequest(
    val user_id: String,
    val password: String,
    val type: String = "B",
    val moduleId: String = "OCR",
)
