package org.journey.android.presentation.main.home.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.home.dto.ResponseHomeDTO

interface HomeController {
    fun getHome(client : String) : Single<ResponseHomeDTO>
}