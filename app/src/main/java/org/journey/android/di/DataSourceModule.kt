package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.presentation.main.badge.data.source.BadgeDataSource
import org.journey.android.presentation.main.badge.data.source.BadgeDataSourceImpl
import org.journey.android.presentation.main.character.data.source.CharacterDataSource
import org.journey.android.presentation.main.character.data.source.CharacterDataSourceImpl
import org.journey.android.presentation.main.community.data.source.CommunityFeedDataSource
import org.journey.android.presentation.main.community.data.source.CommunityFeedDataSourceImpl
import org.journey.android.presentation.main.course.data.source.CourseCatalogDataSource
import org.journey.android.presentation.main.course.data.source.CourseCatalogDataSourceImpl
import org.journey.android.data.network.RetrofitInterface
import org.journey.android.data.network.qualifier.AuthRetrofitService
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

    @Provides
    @Singleton
    fun provideCourseCatalogDataSource(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CourseCatalogDataSource =
        CourseCatalogDataSourceImpl(retrofitInterface)
}