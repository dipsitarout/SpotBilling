package com.example.kotlincourse.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface LoginResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoginResponse(loginResponse: LoginResponseEntity)

    @Query("SELECT * FROM login_response LIMIT 1")
    suspend fun getLoginResponse(): LoginResponseEntity?

    @Query("SELECT * FROM login_response LIMIT 1")
    fun getSavedLoginResponse(): Flow<LoginResponseEntity?>

    @Query("DELETE FROM login_response")
    suspend fun deleteLoginResponse()

    // Naya function - userid aur token return karega

}


