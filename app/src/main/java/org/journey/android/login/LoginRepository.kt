package org.journey.android.login

import io.reactivex.Single

interface LoginRepository {
    fun login(requestLogin: RequestLogin): Single<ResponseLogin>
}