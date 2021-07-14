package org.journey.android.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object JourneyRepository {
    private const val PREF_KEY = "haskhey"
    private const val REFRESH_TOKEN_KEY = "refresh"
    private const val USER_TOKEN_KEY = "access"
    private const val PROGRESS_PERCENT = "affinity"
    private const val USER_JWT = ""
    private const val USER_TOKEN = ""

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private lateinit var encryptedRepository: SharedPreferences

    fun init(context : Context){
        encryptedRepository = EncryptedSharedPreferences.create(
            PREF_KEY,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userRefreshToken: String
        get() = encryptedRepository.getString(REFRESH_TOKEN_KEY, "") ?: ""
        set(value) = encryptedRepository.edit { it.putString(REFRESH_TOKEN_KEY, value) }

    var userAccessToken: String
        get() = encryptedRepository.getString(USER_TOKEN_KEY, "") ?: ""
        set(value) = encryptedRepository.edit { it.putString(USER_TOKEN_KEY, value) }

    var progressPercent: Int
        get() = encryptedRepository.getInt(PROGRESS_PERCENT, -1)
        set(value) = encryptedRepository.edit { it.putInt(PROGRESS_PERCENT, value) }

    var userJwt: String
        get() = encryptedRepository.getString(USER_JWT, "") ?: ""
        set(value) = encryptedRepository.edit { it.putString(USER_JWT, value) }

    var userToken: String
        get() = encryptedRepository.getString(USER_TOKEN, "") ?: ""
        set(value) = encryptedRepository.edit { it.putString(USER_TOKEN, value) }
}