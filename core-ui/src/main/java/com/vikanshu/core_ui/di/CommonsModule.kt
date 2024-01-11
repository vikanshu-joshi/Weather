package com.vikanshu.core_ui.di

import android.content.Context
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.ConnectivityObserverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CommonsModule {

    @Provides
    fun providesConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver {
        return ConnectivityObserverImpl(context)
    }
}