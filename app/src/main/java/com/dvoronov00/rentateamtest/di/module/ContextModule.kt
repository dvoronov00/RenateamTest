package com.dvoronov00.rentateamtest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(
    val context: Context
) {

    @Provides
    fun provideContext() : Context{
        return context.applicationContext
    }
}
