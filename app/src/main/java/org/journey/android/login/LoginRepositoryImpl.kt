package org.journey.android.login

import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
): LoginRepository {
    override fun login(requestLogin: RequestLogin): Single<ResponseLogin> =
        dataSource.login(requestLogin)
}
