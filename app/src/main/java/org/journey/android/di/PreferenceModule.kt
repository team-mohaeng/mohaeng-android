package org.journey.android.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.journey.android.presentation.preference.SharedPreferencesManager
import org.journey.android.presentation.preference.UserPreferenceManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            MOHAENG_SHARED_PREFS,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Provides
    @Singleton
    fun providesSharedPreferenceManager(sharedPreferences: SharedPreferences): SharedPreferencesManager =
        SharedPreferencesManager(sharedPreferences)

    @Provides
    @Singleton
    fun provideUserPreferenceManager(sharedPreferencesManager: SharedPreferencesManager): UserPreferenceManager =
        UserPreferenceManager(sharedPreferencesManager)

    private const val MOHAENG_SHARED_PREFS = "mohaeng_shared_prefs"
}