package com.vikanshu.data.di

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.local.dao.LocationDao
import com.vikanshu.data.repository.LocationRepository
import com.vikanshu.data.repository.impl.LocationRepositoryImpl
import com.vikanshu.data.resource.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_WEATHER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesLocationRepository(
        @Named("io") ioDispatcher: CoroutineDispatcher,
        locationDao: LocationDao,
        weatherApi: WeatherApi
    ): LocationRepository {
        return LocationRepositoryImpl(ioDispatcher, locationDao, weatherApi)
    }

}