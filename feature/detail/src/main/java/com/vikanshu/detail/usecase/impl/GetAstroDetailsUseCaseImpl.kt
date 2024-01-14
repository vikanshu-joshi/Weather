package com.vikanshu.detail.usecase.impl

import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.local.model.Astro
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.detail.usecase.GetAstroDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetAstroDetailsUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository
): GetAstroDetailsUseCase {

    override suspend fun invoke(name: String): CommunicationResult<Astro> {
        return withContext(ioDispatcher) {
            weatherRepository.getAstroDetails(name)
        }
    }

}