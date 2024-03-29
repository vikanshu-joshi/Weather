package com.vikanshu.home.di

import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.home.usecase.GetCurrentWeatherInformationUseCase
import com.vikanshu.home.usecase.GetCurrentWeatherLocationByUserLocationUseCase
import com.vikanshu.home.usecase.impl.GetCurrentWeatherInformationUseCaseImpl
import com.vikanshu.home.usecase.impl.GetCurrentWeatherLocationByUserLocationUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun providesCurrentWeatherInformationUseCase(
        @Named("io") ioDispatcher: CoroutineDispatcher,
        weatherRepository: WeatherRepository
    ): GetCurrentWeatherInformationUseCase {
        return GetCurrentWeatherInformationUseCaseImpl(ioDispatcher, weatherRepository)
    }

    @Provides
    @Singleton
    fun providesCurrentWeatherByUserLocationUseCase(
        @Named("io") ioDispatcher: CoroutineDispatcher,
        weatherRepository: WeatherRepository
    ): GetCurrentWeatherLocationByUserLocationUseCase {
        return GetCurrentWeatherLocationByUserLocationUseCaseImpl(ioDispatcher, weatherRepository)
    }

}