package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.community.data.source.CommunityFeedDataSource
import org.journey.android.community.data.source.CommunityFeedDataSourceImpl
import org.journey.android.network.RetrofitInterface
import org.journey.android.qualifier.AuthRetrofitService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideCommunityFeedDataSource(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CommunityFeedDataSource =
        CommunityFeedDataSourceImpl(retrofitInterface)
}