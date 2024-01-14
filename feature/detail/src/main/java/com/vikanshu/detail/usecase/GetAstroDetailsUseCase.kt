package com.vikanshu.detail.usecase

import com.vikanshu.data.local.model.Astro
import com.vikanshu.data.resource.CommunicationResult

interface GetAstroDetailsUseCase {

    suspend operator fun invoke(name: String): CommunicationResult<Astro>

}