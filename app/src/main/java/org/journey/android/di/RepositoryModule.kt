package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.community.data.repository.CommunityFeedRepository
import org.journey.android.community.data.repository.CommunityFeedRepositoryImpl
import org.journey.android.community.data.source.CommunityFeedDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCommunityFeedRepository(dataSource: CommunityFeedDataSource) : CommunityFeedRepository =
        CommunityFeedRepositoryImpl(dataSource)
}