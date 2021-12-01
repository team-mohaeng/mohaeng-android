package org.journey.android.network

import org.journey.android.network.dto.ResponseAuthDTO
import retrofit2.Response

interface TokenController {
    fun fetchAccessToken(): Response<ResponseAuthDTO>
}