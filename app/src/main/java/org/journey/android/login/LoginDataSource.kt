package org.journey.android.login

import org.journey.android.LoginApiService
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApiService: LoginApiService
) {
    fun login(requestLogin:RequestLogin) = loginApiService.login(requestLogin)
}