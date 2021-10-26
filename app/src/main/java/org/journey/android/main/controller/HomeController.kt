package org.journey.android.main.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.main.dto.ResponseHomeDTO

interface HomeController {
    fun getHome(client : String) : Single<ResponseHomeDTO>
}