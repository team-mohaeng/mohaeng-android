package org.journey.android.main.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.main.dto.ResponseHomeDTO

interface HomeController {
    fun getHome(clent : String) : Single<ResponseHomeDTO>
}