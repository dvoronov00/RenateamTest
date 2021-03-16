package com.dvoronov00.rentateamtest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dvoronov00.rentateamtest.model.User
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers() : Observable<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int) : Observable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(items: List<User>) : Completable
}
