package com.example.kotlincourse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincourse.data.model.OtpRequest
import com.example.kotlincourse.data.model.OtpVerifyRequest
import com.example.kotlincourse.data.repository.OtpRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OtpViewModel(
    private val repository: OtpRepository = OtpRepository()
) : ViewModel() {

    private val _otpSentStatus = MutableStateFlow<String?>(null)
    val otpSentStatus: StateFlow<String?> = _otpSentStatus

    private val _otpVerifyStatus = MutableStateFlow<String?>(null)
    val otpVerifyStatus: StateFlow<String?> = _otpVerifyStatus

    fun sendOtp(email: String, phone: String) {
        viewModelScope.launch {
            try {
                val response = repository.sendOtp(OtpRequest(EMAIL = email, MOB_NUM = phone))
                if (response.isSuccessful && response.body()?.response?.status == true) {
                    _otpSentStatus.value = "OTP Sent Successfully"
                } else {
                    _otpSentStatus.value = "Failed to send OTP"
                }
            } catch (e: Exception) {
                _otpSentStatus.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    fun verifyOtp(mobileNo: String, otp: String) {
        viewModelScope.launch {
            try {
                val response = repository.verifyOtp(OtpVerifyRequest(mobileNo, otp))
                if (response.isSuccessful && response.body()?.response?.status == true) {
                    _otpVerifyStatus.value = "OTP Verified Successfully"
                } else {
                    _otpVerifyStatus.value = "Invalid OTP"
                }
            } catch (e: Exception) {
                _otpVerifyStatus.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}
