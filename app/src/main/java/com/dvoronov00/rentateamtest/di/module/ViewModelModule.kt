package com.dvoronov00.rentateamtest.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dvoronov00.rentateamtest.di.ViewModelFactory
import com.dvoronov00.rentateamtest.di.scope.ViewModelKey
import com.dvoronov00.rentateamtest.ui.user.UserViewModel
import com.dvoronov00.rentateamtest.ui.users.UsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    internal abstract fun usersViewModel(viewModel: UsersViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun userViewModel(viewModel: UserViewModel) : ViewModel


}
