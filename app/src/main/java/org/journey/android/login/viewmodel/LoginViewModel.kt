package org.journey.android.login.viewmodel

import org.journey.android.login.repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository : LoginRepository
)