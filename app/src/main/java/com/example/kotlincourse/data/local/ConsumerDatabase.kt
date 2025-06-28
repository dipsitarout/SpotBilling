package com.example.kotlincourse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ConsumerResponseEntity::class], version = 1)
abstract class ConsumerDatabase : RoomDatabase() {
    abstract fun consumerResponseDao(): ConsumerResponseDao
}
