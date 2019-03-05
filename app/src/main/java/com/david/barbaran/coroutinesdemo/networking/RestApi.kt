package com.david.barbaran.coroutinesdemo.networking

import com.david.barbaran.coroutinesdemo.BuildConfig
import com.david.barbaran.coroutinesdemo.config.Setting
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestApi {

    private lateinit var retrofit: Retrofit
    private val okHttpClient = OkHttpClient
        .Builder()
        .readTimeout(Setting.TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Setting.TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(Setting.TIMEOUT, TimeUnit.SECONDS)
        .build()

    fun instanceClient(): Service {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(Service::class.java)
    }
}