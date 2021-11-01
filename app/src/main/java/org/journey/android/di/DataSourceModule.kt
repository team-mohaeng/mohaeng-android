package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.badge.data.source.BadgeDataSource
import org.journey.android.badge.data.source.BadgeDataSourceImpl
import org.journey.android.character.data.source.CharacterDataSource
import org.journey.android.character.data.source.CharacterDataSourceImpl
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

    @Provides
    @Singleton
    fun provideBadgeDataSource(@AuthRetrofitService retrofitInterface: RetrofitInterface) : BadgeDataSource =
        BadgeDataSourceImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideCharacterDataSource(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CharacterDataSource =
        CharacterDataSourceImpl(retrofitInterface)
}