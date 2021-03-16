package com.dvoronov00.rentateamtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dvoronov00.rentateamtest.model.User

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}
