package org.journey.android.presentation.main.setting.service

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header

interface SettingService {
    @DELETE("/api/delete")
    fun deleteUser(
        @Header("Content-Type") contenttype: String,
        @Header("Bearer") jwt: String,
    ) : Call<Unit>
}