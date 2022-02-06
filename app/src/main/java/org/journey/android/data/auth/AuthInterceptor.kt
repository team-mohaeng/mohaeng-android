package org.journey.android.data.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.journey.android.presentation.preference.UserPreferenceManager
import java.net.HttpURLConnection
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val tokenController: TokenController,
    private val userPreferenceManager: UserPreferenceManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeaders(userPreferenceManager.fetchUserAccessToken()).build()
        val response = chain.proceed(request)

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            val tokenRefreshed = tokenController.fetchAccessToken()

            if (tokenRefreshed.isSuccessful) {
                userPreferenceManager.saveUserAccessToken(tokenRefreshed.body()!!.accessToken)
                userPreferenceManager.saveUserRefreshToken(tokenRefreshed.body()!!.refreshToken)
                val newRequest = chain.request().newBuilder()
                    .addHeaders(userPreferenceManager.fetchUserAccessToken())
                    .build()

                return chain.proceed(newRequest)
            }
        }
        return response
    }

    private fun Request.Builder.addHeaders(token: String) =
        this.apply { header(AUTH_HEADER_KEY, token) }

    companion object {
        private const val AUTH_HEADER_KEY = "X-AUTH-TOKEN"
    }
}