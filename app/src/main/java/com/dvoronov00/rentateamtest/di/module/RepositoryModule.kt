package com.dvoronov00.rentateamtest.di.module

import com.dvoronov00.rentateamtest.database.UserDao
import com.dvoronov00.rentateamtest.network.ApiService
import com.dvoronov00.rentateamtest.repository.UserRepository
import com.dvoronov00.rentateamtest.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(apiService: ApiService, userDao: UserDao) : UserRepository{
        return UserRepositoryImpl(apiService, userDao)
    }

}
