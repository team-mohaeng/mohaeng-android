package org.journey.android.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object RespositoryModule {
    val journeyRespository by lazy {
    }
}