package com.vikanshu.weather.di

import com.vikanshu.weather.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("io")) { Dispatchers.IO }
    single(named("default")) { Dispatchers.Default }
    viewModel {
        HomeViewModel(get(named("io")), get())
    }
}