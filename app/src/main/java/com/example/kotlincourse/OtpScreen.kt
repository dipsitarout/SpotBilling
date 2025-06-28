package com.example.kotlincourse.ui

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotlincourse.viewmodel.OtpViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(navController: NavController, viewModel: OtpViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var showOtpDialog by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }
    var otpError by remember { mutableStateOf<String?>(null) }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPhoneValid = phone.length == 10 && phone.all { it.isDigit() }
    val isSendOtpEnabled = isEmailValid && isPhoneValid

    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    var resendTimer by remember { mutableStateOf(60) }
    var canResend by remember { mutableStateOf(false) }

    val otpDigits = remember { mutableStateListOf("", "", "", "", "", "") }
    val allDigitsEntered = otpDigits.all { it.length == 1 }
    var lastVerificationFailed by remember { mutableStateOf(false) }
    var otpInputChangedAt by remember { mutableStateOf(0L) }

    // Gradient background colors (same as splash)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0D47A1),
            Color(0xFF1976D2),
            Color(0xFF42A5F5)
        )
    )

    LaunchedEffect(showOtpDialog) {
        if (showOtpDialog) {
            otpError = null
            canResend = false
            resendTimer = 60
            while (resendTimer > 0) {
                delay(1000L)
                resendTimer--
            }
            canResend = true
        }
    }

    LaunchedEffect(Unit) {
        viewModel.otpSentStatus.collect { status ->
            status?.let {
                message = it
                if (it.contains("Successfully", ignoreCase = true)) {
                    showOtpDialog = true
                    focusManager.clearFocus()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.otpVerifyStatus.collect { status ->
            status?.let {
                message = it
                if (it.contains("Verified", ignoreCase = true) || it.contains("Success", ignoreCase = true)) {
                    otpError = null
                    lastVerificationFailed = false
                    navController.navigate("Login2") {
                        popUpTo("Login") { inclusive = true }
                    }
                } else if (showOtpDialog) {
                    otpError = it
                    lastVerificationFailed = true
                }
            }
        }
    }

    LaunchedEffect(otpInputChangedAt) {
        if (lastVerificationFailed && allDigitsEntered) {
            delay(500L)
            viewModel.verifyOtp(phone.trim(), otpDigits.joinToString(""))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "OTP Verification",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0D47A1),
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = Color(0xFF1976D2)) },
                    singleLine = true,
                    isError = email.isNotEmpty() && !isEmailValid,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1976D2),
                        unfocusedBorderColor = Color(0xFF90CAF9),
                        cursorColor = Color(0xFF1976D2)
                    )

                )
                if (email.isNotEmpty() && !isEmailValid) {
                    Text(
                        "Invalid email address",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, top = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number", color = Color(0xFF1976D2)) },
                    singleLine = true,
                    isError = phone.isNotEmpty() && !isPhoneValid,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1976D2),
                        unfocusedBorderColor = Color(0xFF90CAF9),
                        cursorColor = Color(0xFF1976D2)
                    )

                )
                if (phone.isNotEmpty() && !isPhoneValid) {
                    Text(
                        "Phone must be 10 digits",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, top = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))

                Button(
                    onClick = { viewModel.sendOtp(email.trim(), phone.trim()) },
                    enabled = isSendOtpEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text(
                        "Send OTP",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                message?.let {
                    Text(
                        text = it,
                        color = if (it.contains("Success", ignoreCase = true)) Color(0xFF2E7D32) else Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp
                    )
                }
            }
        }

        if (showOtpDialog) {
            OtpInputDialog(
                phone = phone.trim(),
                onDismiss = {
                    showOtpDialog = false
                    otpError = null
                    lastVerificationFailed = false
                    otpDigits.fill("")
                },
                onVerify = { otp ->
                    otpError = null
                    lastVerificationFailed = false
                    viewModel.verifyOtp(phone.trim(), otp)
                },
                onResend = {
                    otpError = null
                    lastVerificationFailed = false
                    viewModel.sendOtp(email.trim(), phone.trim())
                    showOtpDialog = true
                    otpDigits.fill("")
                },
                canResend = canResend,
                resendTimer = resendTimer,
                otpError = otpError,
                otpDigits = otpDigits,
                onOtpChange = { index, value ->
                    otpDigits[index] = value
                    otpError = null
                    if (lastVerificationFailed && otpDigits.all { it.length == 1 }) {
                        otpInputChangedAt = System.currentTimeMillis()
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OtpInputDialog(
    phone: String,
    onDismiss: () -> Unit,
    onVerify: (String) -> Unit,
    onResend: () -> Unit,
    canResend: Boolean,
    resendTimer: Int,
    otpError: String?,
    otpDigits: MutableList<String>,
    onOtpChange: (index: Int, value: String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequesters = List(6) { FocusRequester() }
    val allDigitsEntered = otpDigits.all { it.length == 1 }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Enter OTP",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color(0xFF0D47A1)
            )
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "OTP sent to +91-$phone",
                    fontSize = 14.sp,
                    color = Color(0xFF1976D2),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth(),
                ) {
                    otpDigits.forEachIndexed { index, digit ->
                        OutlinedTextField(
                            value = digit,
                            onValueChange = {
                                if (it.length <= 1 && (it.isEmpty() || it[0].isDigit())) {
                                    onOtpChange(index, it)
                                    if (it.length == 1 && index < 5) {
                                        focusRequesters[index + 1].requestFocus()
                                    }
                                    if (it.isEmpty() && index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(48.dp)
                                .focusRequester(focusRequesters[index]),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color(0xFF0D47A1),
                                fontWeight = FontWeight.Bold
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = if (index == 5) ImeAction.Done else ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                    if (allDigitsEntered) {
                                        onVerify(otpDigits.joinToString(""))
                                    }
                                }
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF1976D2),
                                unfocusedBorderColor = Color(0xFF90CAF9),
                                cursorColor = Color(0xFF1976D2)
                            )
                            ,
                            isError = otpError != null
                        )
                    }
                }

                otpError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        enabled = canResend,
                        onClick = onResend,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (canResend) Color(0xFF1976D2) else Color.Gray
                        )
                    ) {
                        Text(
                            if (canResend) "Resend OTP" else "Resend in 00:${if (resendTimer < 10) "0$resendTimer" else resendTimer}"
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { if (allDigitsEntered) onVerify(otpDigits.joinToString("")) },
                enabled = allDigitsEntered,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
            ) {
                Text("Verify", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color(0xFF1976D2))
            }
        }
    )
}