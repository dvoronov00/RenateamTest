package com.dvoronov00.rentateamtest.repository

import com.dvoronov00.rentateamtest.database.UserDao
import com.dvoronov00.rentateamtest.model.User
import com.dvoronov00.rentateamtest.network.ApiService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(): Observable<List<User>> {
        return getFromNetwork().onErrorResumeNext(getFromStorage())
    }

    private fun getFromStorage(): Observable<List<User>> {
        return userDao.getAllUsers().doOnNext {
            if(it.isEmpty()) throw Exception()
        }
    }

    private fun getFromNetwork(): Observable<List<User>> {
        return apiService.getUsers().map { response ->
            response.data
        }.doOnNext { users ->
            saveToStorage(users = users)
        }
    }

    private fun saveToStorage(users: List<User>) {
        userDao.saveUsers(users)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
