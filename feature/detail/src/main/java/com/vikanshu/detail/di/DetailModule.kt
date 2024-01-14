package com.vikanshu.detail.di

import com.vikanshu.data.repository.ForecastRepository
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.detail.usecase.GetAstroDetailsUseCase
import com.vikanshu.detail.usecase.GetForecastDetailsUseCase
import com.vikanshu.detail.usecase.impl.GetAstroDetailsUseCaseImpl
import com.vikanshu.detail.usecase.impl.GetForecastDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetailModule {

    @Provides
    @Singleton
    fun providesAstroDetailsUseCase(
        @Named("io") ioDispatcher: CoroutineDispatcher,
        weatherRepository: WeatherRepository
    ): GetAstroDetailsUseCase {
        return GetAstroDetailsUseCaseImpl(ioDispatcher, weatherRepository)
    }

    @Provides
    @Singleton
    fun providesForecastDetailsUseCase(
        @Named("io") ioDispatcher: CoroutineDispatcher,
        forecastRepository: ForecastRepository
    ): GetForecastDetailsUseCase {
        return GetForecastDetailsUseCaseImpl(ioDispatcher, forecastRepository)
    }

}