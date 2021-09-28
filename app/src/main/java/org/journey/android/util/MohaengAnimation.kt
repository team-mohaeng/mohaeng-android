package org.journey.android.util

import android.app.Activity
import org.journey.android.R

fun Activity.slideUp(){
    overridePendingTransition(R.anim.slide_up,R.anim.slide_up_exit)
}