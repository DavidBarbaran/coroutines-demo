package com.david.barbaran.coroutinesdemo.controller

import com.david.barbaran.coroutinesdemo.model.User

interface MainController {
    interface View {
        fun onLoadUserSuccessful(list : List<User>)
        fun onLoadUserFailed(message : String)
    }

    interface Presenter {
        suspend fun loadUsers()
    }
}