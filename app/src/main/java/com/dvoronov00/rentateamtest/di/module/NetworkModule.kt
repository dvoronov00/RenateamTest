package com.dvoronov00.rentateamtest.di.module

import com.dvoronov00.rentateamtest.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {
    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService() : ApiService{
        return provideRetrofit().create(ApiService::class.java)
    }
}
