package org.journey.android.login.repository

import io.reactivex.Single
import org.journey.android.login.di.LoginDataSource
import org.journey.android.login.model.RequestLogin
import org.journey.android.login.model.ResponseLogin
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
): LoginRepository {
    override fun login(requestLogin: RequestLogin): Single<ResponseLogin> =
        dataSource.login(requestLogin)
}
