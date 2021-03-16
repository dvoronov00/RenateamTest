package com.dvoronov00.rentateamtest.network

import com.dvoronov00.rentateamtest.model.UsersResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers() : Observable<UsersResponse>
}
