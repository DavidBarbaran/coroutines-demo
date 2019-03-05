package com.david.barbaran.coroutinesdemo.presenter

import android.content.Context
import com.david.barbaran.coroutinesdemo.R
import com.david.barbaran.coroutinesdemo.controller.MainController
import com.david.barbaran.coroutinesdemo.networking.RestApi
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class MainPresenter(private val context: Context) : MainController.Presenter {

    private val restApi = RestApi.instanceClient()
    lateinit var controller: MainController.View

    override suspend fun loadUsers() {
        try {
            val response = restApi.getUsers().await()
            if (response.isSuccessful) {
                controller.onLoadUserSuccessful(response.body()!!)
            } else {
                controller.onLoadUserFailed(context.getString(R.string.server_error_message))
            }
        } catch (ex: Exception) {
            controller.onLoadUserFailed(evaluateFailure(context, ex))
        }
    }

    private fun evaluateFailure(context: Context, t: Throwable): String {
        return when (t) {
            is UnknownHostException -> context.getString(R.string.connection_message)
            is SocketTimeoutException -> context.getString(R.string.time_out_message)
            is SSLHandshakeException -> context.getString(R.string.connection_lost_message)
            else -> context.getString(R.string.default_error_message)
        }
    }
}