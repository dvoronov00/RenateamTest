package com.dvoronov00.rentateamtest.ui.users

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dvoronov00.rentateamtest.model.DataState
import com.dvoronov00.rentateamtest.model.User
import com.dvoronov00.rentateamtest.repository.UserRepository
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val usersRelay: BehaviorRelay<DataState<List<User>>> = BehaviorRelay.create()

    private var disposable: Disposable? = null

    fun getUsers() {
        disposable?.dispose()
        disposable = userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                usersRelay.accept(DataState.Loading())
            }
            .subscribe({
                usersRelay.accept(DataState.Success(it))
            }, {
                usersRelay.accept(DataState.Failure(it))
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
