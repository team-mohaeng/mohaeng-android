package org.journey.android.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.journey.android.util.AuthInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObjects {
    private const val BASE_URL = "http://3.36.55.247:5000"
    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(httpLoggingInterceptor())
        .build()

    private val baseRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private var mainInstance: MainService? = null
    fun getMainService(): MainService = mainInstance ?: synchronized(this) {
        mainInstance ?: baseRetrofit.create(MainService::class.java).apply {
            mainInstance = this
        }
    }
}