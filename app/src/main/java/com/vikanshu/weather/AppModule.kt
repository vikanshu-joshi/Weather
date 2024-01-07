package com.vikanshu.weather

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("io")) { Dispatchers.IO }
    single(named("default")) { Dispatchers.Default }
}