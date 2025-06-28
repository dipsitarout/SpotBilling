package com.example.kotlincourse.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ConsumerResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(consumerResponseEntity: ConsumerResponseEntity)

    @Query("SELECT * FROM consumer_response LIMIT 1")
    suspend fun getConsumerResponse(): ConsumerResponseEntity?

    @Query("DELETE FROM consumer_response")
    suspend fun clearAll()
}
