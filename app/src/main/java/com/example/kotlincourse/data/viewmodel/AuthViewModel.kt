package com.example.kotlincourse.data.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincourse.data.local.AppDatabase
import com.example.kotlincourse.data.local.LoginResponseDao
import com.example.kotlincourse.data.local.LoginResponseEntity
import com.example.kotlincourse.data.model.LoginResponse
import com.example.kotlincourse.data.repository.AuthRepository
import com.example.kotlincourse.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository()
    private val sessionManager = SessionManager(application.applicationContext)
    private val loginResponseDao = AppDatabase.getDatabase(application).loginResponseDao()

    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult: StateFlow<Result<String>?> = _loginResult

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    fun logoutUser() {
        viewModelScope.launch {
            try {
                val user = loginResponseDao.getLoginResponse()
                if (user != null) {
                    loginResponseDao.deleteLoginResponse()
                    Toast.makeText(getApplication(), "Logged out successfully ✅", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(getApplication(), "No user to logout ❗", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(getApplication(), "Error during logout: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }




    val savedToken: Flow<String?> get() = sessionManager.getToken
    fun getSavedLoginResponse(): Flow<LoginResponseEntity?> {
        return loginResponseDao.getSavedLoginResponse()
    }
    fun login(userId: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(userId, password, "B", "OCR")
                if (response.isSuccessful) {
                    val loginResp = response.body()

                    if (loginResp == null || loginResp.token.isNullOrBlank()) {
                        _loginResult.emit(Result.failure(Exception("Login failed: Token is empty or response is null")))
                        return@launch
                    }

                    // Save token asynchronously
                    sessionManager.saveToken(loginResp.token)

                    // Emit login response to UI observers
                    _loginResponse.emit(loginResp)

                    // Save LoginResponseEntity to local DB on IO thread
                    launch(Dispatchers.IO) {
                        val localLoginResp = LoginResponseEntity(
                            user_id = loginResp.user_id,
                            status = loginResp.status,
                            status_code = loginResp.status_code,
                            message = loginResp.message,
                            server_date_time = loginResp.server_date_time,
                            software_version_no = loginResp.software_version_no,
                            address = loginResp.address,
                            flag = loginResp.flag,
                            sbm_billing = loginResp.sbm_billing,
                            non_sbm_billing = loginResp.non_sbm_billing,
                            bill_distribution_flag = loginResp.bill_distribution_flag,
                            quality_check_flag = loginResp.quality_check_flag,
                            theft_flag = loginResp.theft_flag,
                            consumer_fb_flag = loginResp.consumer_fb_flag,
                            extra_conn_flag = loginResp.extra_conn_flag,
                            bill_flag = loginResp.bill_flag,
                            account_coll_flag = loginResp.account_coll_flag,
                            db_server_user_name = loginResp.db_server_user_name,
                            db_server_password = loginResp.db_server_password,
                            div_code = loginResp.div_code,
                            token = loginResp.token,
                            module_id = loginResp.module_id,
                            software_version_sap = loginResp.software_version_sap,
                            urlname = loginResp.urlname,
                            apkname = loginResp.apkname,
                            versionValidateFlag = loginResp.versionValidateFlag,
                            mobileValidateFlag = loginResp.mobileValidateFlag,
                            holidayDateList = loginResp.holidayDateList
                        )
                        loginResponseDao.insertLoginResponse(localLoginResp)
                    }

                    _loginResult.emit(Result.success(loginResp.token))
                } else {
                    // Handle error response body properly
                    val errorMessage = try {
                        val errorJson = response.errorBody()?.string()
                        val jsonObject = JSONObject(errorJson ?: "")
                        jsonObject.optString("error", "Unknown error occurred")
                    } catch (e: Exception) {
                        "Login failed with code: ${response.code()}"
                    }
                    _loginResult.emit(Result.failure(Exception(errorMessage)))
                }
            } catch (e: HttpException) {
                _loginResult.emit(Result.failure(Exception("Network error: ${e.message()}")))
            } catch (e: Exception) {
                _loginResult.emit(Result.failure(e))
            }
        }
    }

    // Logout clears token and resets states
    fun logout() {
        viewModelScope.launch {
            sessionManager.clearToken()
            _loginResponse.emit(null)
            _loginResult.emit(null)
        }
    }
}
