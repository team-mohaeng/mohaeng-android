package org.journey.android.data.auth

import org.journey.android.data.auth.dto.ResponseAuthDTO
import retrofit2.Response

interface TokenController {
    fun fetchAccessToken(): Response<ResponseAuthDTO>
}