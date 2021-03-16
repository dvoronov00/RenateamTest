package com.dvoronov00.rentateamtest

import android.app.Application
import android.content.Context
import android.util.Log
import com.dvoronov00.rentateamtest.di.AppComponent
import com.dvoronov00.rentateamtest.di.DaggerAppComponent
import com.dvoronov00.rentateamtest.di.module.ContextModule
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {
    companion object{
        private lateinit var component: AppComponent
        fun getComponent(): AppComponent{
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable -> Log.e("App", throwable.toString()) }
        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}
