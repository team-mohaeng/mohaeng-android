package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.presentation.main.badge.data.repository.BadgeListRepository
import org.journey.android.presentation.main.badge.data.repository.BadgeListRepositoryImpl
import org.journey.android.presentation.main.badge.data.source.BadgeDataSource
import org.journey.android.presentation.main.character.data.repository.CharacterRepository
import org.journey.android.presentation.main.character.data.repository.CharacterRepositoryImpl
import org.journey.android.presentation.main.character.data.source.CharacterDataSource
import org.journey.android.presentation.main.community.data.repository.CommunityFeedRepository
import org.journey.android.presentation.main.community.data.repository.CommunityFeedRepositoryImpl
import org.journey.android.presentation.main.community.data.source.CommunityFeedDataSource
import org.journey.android.presentation.main.course.data.repository.CourseCatalogRepository
import org.journey.android.presentation.main.course.data.repository.CourseCatalogRepositoryImpl
import org.journey.android.presentation.main.course.data.source.CourseCatalogDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCommunityFeedRepository(dataSource: CommunityFeedDataSource) : CommunityFeedRepository =
        CommunityFeedRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideBadgeListRepository(dataSource : BadgeDataSource) : BadgeListRepository =
        BadgeListRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideCourseCatalogRepository(dataSource : CourseCatalogDataSource) : CourseCatalogRepository =
        CourseCatalogRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideCharacerRepository(datasource : CharacterDataSource) : CharacterRepository =
        CharacterRepositoryImpl(datasource)
}