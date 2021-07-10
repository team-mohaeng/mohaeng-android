package org.journey.android.util

import android.content.SharedPreferences
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SharedPrefUtil @Inject constructor(
    val encryptedPrefs: SharedPreferences
) {
    fun <T> setPrefs(key: String, value: T) = with(encryptedPrefs.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Long -> putLong(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> IllegalArgumentException("Preferences type error")
        }
        apply()
    }

    fun <T> getPrefs(key: String, defaultValue: T): T {
        encryptedPrefs.run {
            return when (defaultValue) {
                is String -> getString(key, defaultValue) as T
                is Int -> getInt(key, defaultValue) as T
                is Long -> getLong(key, defaultValue) as T
                is Boolean -> getBoolean(key, defaultValue) as T
                is Float -> getFloat(key, defaultValue) as T
                else -> throw IllegalArgumentException("Preferences Type Error")
            }
        }
    }
}