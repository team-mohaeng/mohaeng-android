package org.journey.android.login.viewmodel

import org.journey.android.login.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository : LoginRepository
)