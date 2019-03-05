package com.david.barbaran.coroutinesdemo.networking

import com.david.barbaran.coroutinesdemo.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("data.json")
    fun getUsers(): Deferred<Response<List<User>>>
}