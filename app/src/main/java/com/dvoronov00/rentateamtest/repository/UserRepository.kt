package com.dvoronov00.rentateamtest.repository

import com.dvoronov00.rentateamtest.model.User
import io.reactivex.Observable

interface UserRepository {
    fun getUsers() : Observable<List<User>>
}
