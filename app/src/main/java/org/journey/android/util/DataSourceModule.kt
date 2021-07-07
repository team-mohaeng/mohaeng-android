package org.journey.android.util

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.login.di.LoginDataSource
import org.journey.android.login.model.LoginApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideLoginDataSource(loginApiService: LoginApiService) = LoginDataSource(loginApiService)
}