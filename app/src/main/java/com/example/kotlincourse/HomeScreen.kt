package com.example.kotlincourse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kotlincourse.data.session.SessionManager
import com.example.kotlincourse.data.viewmodel.AuthViewModel
import com.example.kotlincourse.data.viewmodel.ConsumerViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
   val context = LocalContext.current
   val sessionManager = remember { SessionManager(context) }

   var expanded by remember { mutableStateOf(false) }
   val authViewModel: AuthViewModel = viewModel()
   val consumerViewModel: ConsumerViewModel = viewModel()

   var consumerNumber by remember { mutableStateOf("") }
   var refNo by remember { mutableStateOf("") }
   var billType by remember { mutableStateOf("11") }
   var userId by remember { mutableStateOf("") }

   val consumerData by consumerViewModel.consumerData.collectAsState()
   val error by consumerViewModel.error.collectAsState(initial = null)
   val coroutineScope = rememberCoroutineScope()

   Scaffold(
      topBar = {
         TopAppBar(
            title = { Text("Home", fontWeight = FontWeight.Bold) },
            actions = {
               Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                  IconButton(onClick = { expanded = true }) {
                     Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.White
                     )
                  }
                  DropdownMenu(
                     expanded = expanded,
                     onDismissRequest = { expanded = false },
                     modifier = Modifier.background(Color.White)
                  ) {
                     DropdownMenuItem(text = { Text("Name: Admin User") }, onClick = {})
                     DropdownMenuItem(text = { Text("Email: admin@example.com") }, onClick = {})
                     DropdownMenuItem(
                        text = { Text("Go to Dashboard") },
                        onClick = {
                           expanded = false
                           navController.navigate("UserDashboard") {
                              popUpTo("home") { inclusive = false }
                           }
                        }
                     )
                     DropdownMenuItem(
                        text = { Text("Logout", color = Color.Red) },
                        onClick = {
                           expanded = false
                           coroutineScope.launch {
                              authViewModel.logoutUser()
                              sessionManager.clearToken()
                              navController.navigate("Login") {
                                 popUpTo("home") { inclusive = true }
                              }
                           }
                        }
                     )
                  }
               }
            },
            colors = TopAppBarDefaults.topAppBarColors(
               containerColor = Color(0xFF1565C0),
               titleContentColor = Color.White
            )
         )
      },
      containerColor = Color(0xFFF5F5F5)
   ) { paddingValues ->
      LazyColumn(
         modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 12.dp),
         verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
         item {
            OutlinedTextField(
               value = consumerNumber,
               onValueChange = { consumerNumber = it },
               label = { Text("Consumer Number") },
               modifier = Modifier.fillMaxWidth()
            )
         }
         item {
            OutlinedTextField(
               value = refNo,
               onValueChange = { refNo = it },
               label = { Text("Ref No") },
               modifier = Modifier.fillMaxWidth()
            )
         }
         item {
            OutlinedTextField(
               value = billType,
               onValueChange = { billType = it },
               label = { Text("Bill Type") },
               modifier = Modifier.fillMaxWidth()
            )
         }
         item {
            OutlinedTextField(
               value = userId,
               onValueChange = { userId = it },
               label = { Text("User ID") },
               modifier = Modifier.fillMaxWidth()
            )
         }
         item {
            Button(
               onClick = {
                  consumerViewModel.getconsumerData(
                     consumerNumber = consumerNumber,
                     refNo = refNo,
                     billType = billType,
                     userId = userId
                  )
               },
               modifier = Modifier
                  .fillMaxWidth()
                  .height(50.dp)
            ) {
               Text("Fetch Data", fontSize = 16.sp)
            }
         }
         item {
            error?.let {
               Text(text = "Error: $it", color = Color.Red, fontSize = 14.sp)
            }
         }
         item {
            consumerData?.let { data ->
               Card(
                  modifier = Modifier
                     .fillMaxWidth()
                     .padding(top = 8.dp),
                  elevation = CardDefaults.cardElevation(6.dp),
                  shape = MaterialTheme.shapes.medium,
                  colors = CardDefaults.cardColors(containerColor = Color.White)
               ) {
                  Column(modifier = Modifier.padding(20.dp)) {
                     Text(
                        text = "Consumer Details",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1565C0)
                     )
                     Spacer(modifier = Modifier.height(12.dp))

                     if (!data.CNSMR_NM.isNullOrBlank())
                        InfoRow(label = "Name", value = data.CNSMR_NM)
                     if (!data.AMNT_PYBL_RNDD.isNullOrBlank())
                        InfoRow(label = "Amount Payable", value = "₹${data.AMNT_PYBL_RNDD}")
                     if (!data.BLL_MNTH.isNullOrBlank() && !data.BLL_YR.isNullOrBlank())
                        InfoRow(label = "Bill Month", value = "${data.BLL_MNTH} / ${data.BLL_YR}")
                     if (!data.KNO.isNullOrBlank())
                        InfoRow(label = "KNO", value = data.KNO)
                     if (!data.ADD1.isNullOrBlank())
                        InfoRow(label = "Address 1", value = data.ADD1)
                     if (!data.ADD2.isNullOrBlank())
                        InfoRow(label = "Address 2", value = data.ADD2)
                     if (!data.MOBILE_NO.isNullOrBlank())
                        InfoRow(label = "MobileNo", value = data.MOBILE_NO)
                  }
               }
            }



         }
      }
   }
}

@Composable
fun InfoRow(label: String, value: String) {
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .padding(vertical = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      Text(text = label, fontSize = 15.sp, fontWeight = FontWeight.Medium)
      Text(text = value, fontSize = 15.sp)
   }
}
