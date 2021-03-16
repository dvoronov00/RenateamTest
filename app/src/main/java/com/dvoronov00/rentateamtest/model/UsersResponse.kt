package com.dvoronov00.rentateamtest.model

data class UsersResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val data: List<User>
)
