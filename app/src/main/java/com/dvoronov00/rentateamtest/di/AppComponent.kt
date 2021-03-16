package com.dvoronov00.rentateamtest.di

import android.content.Context
import com.dvoronov00.rentateamtest.di.module.DatabaseModule
import com.dvoronov00.rentateamtest.di.module.NetworkModule
import com.dvoronov00.rentateamtest.di.module.RepositoryModule
import com.dvoronov00.rentateamtest.di.module.ViewModelModule
import com.dvoronov00.rentateamtest.ui.user.UserFragment
import com.dvoronov00.rentateamtest.ui.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {
    fun context(): Context

    fun inject(fragment: UsersFragment)
    fun inject(fragment: UserFragment)
}
