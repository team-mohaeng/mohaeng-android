package org.journey.android.util

import android.app.Activity
import android.app.Application
import android.os.Bundle

abstract class SimpleActivityLifecycleCallbacks(private val targetActivity: Activity) :
    Application.ActivityLifecycleCallbacks {
    abstract fun onActivityCreated()
    abstract fun onActivityDestroyed()

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        if (targetActivity == activity) {
            onActivityCreated()
        }
    }
    override fun onActivityDestroyed(activity: Activity) {
        if (targetActivity == activity) {
            targetActivity.application.unregisterActivityLifecycleCallbacks(this)
            onActivityDestroyed()
        }
    }
    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

}