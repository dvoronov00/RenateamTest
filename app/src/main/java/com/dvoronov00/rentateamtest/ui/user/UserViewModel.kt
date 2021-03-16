package com.dvoronov00.rentateamtest.ui.user

import androidx.lifecycle.ViewModel
import com.dvoronov00.rentateamtest.database.UserDao
import com.dvoronov00.rentateamtest.model.User
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    val userRelay: BehaviorRelay<User> = BehaviorRelay.create()

    private var disposable: Disposable? = null

    fun getUser(userId: Int) {
        disposable = userDao.getUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                userRelay.accept(it)
            }
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

}
