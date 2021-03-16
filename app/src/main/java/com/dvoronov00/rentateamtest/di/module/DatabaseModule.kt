package com.dvoronov00.rentateamtest.di.module

import android.content.Context
import androidx.room.Room
import com.dvoronov00.rentateamtest.database.UserDao
import com.dvoronov00.rentateamtest.database.UsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DatabaseModule {
    companion object{
        private const val DB_NAME = "Users"
    }

    @Provides
    @Singleton
    fun providesRoomDatabase(context: Context) : UsersDatabase {
        return Room.databaseBuilder(context, UsersDatabase::class.java, DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun providesUserDao(usersDatabase: UsersDatabase): UserDao {
        return usersDatabase.userDao()
    }
}
