package com.david.barbaran.coroutinesdemo.module

import com.david.barbaran.coroutinesdemo.adapter.UserAdapter
import com.david.barbaran.coroutinesdemo.presenter.MainPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val presenterModule = module {
    factory { MainPresenter(androidContext()) }
}

val adapterModule = module {
    factory { UserAdapter() }
}