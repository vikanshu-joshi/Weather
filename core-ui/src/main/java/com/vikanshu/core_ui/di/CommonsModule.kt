package com.vikanshu.core_ui.di

import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.ConnectivityObserverImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    single<ConnectivityObserver> {
        ConnectivityObserverImpl(androidContext())
    }
}