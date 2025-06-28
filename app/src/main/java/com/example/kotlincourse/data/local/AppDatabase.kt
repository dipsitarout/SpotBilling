package com.example.kotlincourse.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlincourse.utils.Converters

@Database(
    entities = [LoginResponseEntity::class, ConsumerResponseEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loginResponseDao(): LoginResponseDao
    abstract fun consumerResponseDao(): ConsumerResponseDao // lowercase 'c' for consistency

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database"
                ).fallbackToDestructiveMigration() // optional but helpful for dev
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
