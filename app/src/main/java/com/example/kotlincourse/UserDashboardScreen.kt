package com.example.kotlincourse

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotlincourse.data.local.LoginResponseEntity
import com.example.kotlincourse.data.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var userData by remember { mutableStateOf<LoginResponseEntity?>(null) }

    LaunchedEffect(Unit) {
        authViewModel.getSavedLoginResponse().collectLatest {
            userData = it
        }
    }

    val backgroundColor = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0D47A1),
            Color(0xFF1976D2),
            Color(0xFF42A5F5)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("home") {
                            popUpTo("UserDashboard") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Go to Home",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF1976D2),
                contentColor = Color.White
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "© TPCODL",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "User Dashboard",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
                )

                if (userData != null) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            UserDataRow("User ID", userData!!.user_id)
//                            UserDataRow("Status", userData!!.status.toString())
//                            UserDataRow("Status Code", userData!!.status_code.toString())
//                            UserDataRow("Message", userData!!.message)
                            UserDataRow("Server Date Time", userData!!.server_date_time)
                            UserDataRow("Software Version", userData!!.software_version_no)
                            UserDataRow("SAP Version", userData!!.software_version_sap)
                            UserDataRow("Address", userData!!.address ?: "N/A")
                            UserDataRow("Module ID", userData!!.module_id)
                            UserDataRow("Flag", userData!!.flag.toString())
                            UserDataRow("SBM Billing", userData!!.sbm_billing.toString())
                            UserDataRow("Non-SBM Billing", userData!!.non_sbm_billing.toString())
                            UserDataRow("Bill Distribution", userData!!.bill_distribution_flag.toString())
                            UserDataRow("Quality Check", userData!!.quality_check_flag.toString())
                            UserDataRow("Theft", userData!!.theft_flag.toString())
                            UserDataRow("Consumer Feedback", userData!!.consumer_fb_flag.toString())
                            UserDataRow("Extra Connection", userData!!.extra_conn_flag.toString())
                            UserDataRow("Bill Flag", userData!!.bill_flag.toString())
                            UserDataRow("Account Collection", userData!!.account_coll_flag.toString())
                            UserDataRow("DB Username", userData!!.db_server_user_name)
                            UserDataRow("DB Password", userData!!.db_server_password)
                            UserDataRow("Division Code", userData!!.div_code)
                            UserDataRow("URL Name", userData!!.urlname)
                            UserDataRow("APK Name", userData!!.apkname)
                            UserDataRow("Version Validate Flag", userData!!.versionValidateFlag)
                            UserDataRow("Mobile Validate Flag", userData!!.mobileValidateFlag)
                            UserDataRow("Holiday Dates", userData!!.holidayDateList.joinToString())
                        }
                    }
                } else {
                    Text(
                        text = "Loading user data...",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun UserDataRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            modifier = Modifier.weight(2f)
        )
    }
}