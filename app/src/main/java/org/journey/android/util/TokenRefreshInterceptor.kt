package org.journey.android.util

import okhttp3.Interceptor
import okhttp3.Response
import org.journey.android.data.JourneyRepository

class TokenRefreshInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val reQuestBuilder = chain.request().newBuilder()

        JourneyRepository.userRefreshToken.let{
            reQuestBuilder.addHeader("refresh token", it)
        }
        return chain.proceed(reQuestBuilder.build())
    }
}