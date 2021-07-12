package org.journey.android.util

import okhttp3.Interceptor
import okhttp3.Response
import org.journey.android.data.JourneyRepository

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        if (JourneyRepository.userAccessToken != "" ) {
            JourneyRepository.userAccessToken.let {
                requestBuilder.addHeader("token", it)
            }
        }
        return chain.proceed(requestBuilder.build())
    }

}