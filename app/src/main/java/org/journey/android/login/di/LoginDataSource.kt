package org.journey.android.login.di

import org.journey.android.login.model.LoginApiService
import org.journey.android.login.model.RequestLogin
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApiService: LoginApiService
) {
    fun login(requestLogin: RequestLogin) = loginApiService.login(requestLogin)
}