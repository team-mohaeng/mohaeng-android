package org.journey.android.login.repository

import io.reactivex.Single
import org.journey.android.login.model.RequestLogin
import org.journey.android.login.model.ResponseLogin

interface LoginRepository {
    fun login(requestLogin: RequestLogin): Single<ResponseLogin>
}