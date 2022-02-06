package org.journey.android.presentation.util

import android.app.Activity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject


class TedRxKeyboardObserver(activity: Activity) : BaseKeyboardObserver(activity) {
    private val behaviorSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    fun listen(): Observable<Boolean> {
        internalListen(object : OnKeyboardListener {
            override fun onKeyboardChange(isShow: Boolean) {
                behaviorSubject.onNext(isShow)
            }
        })
        return behaviorSubject
    }

    override fun onActivityDestroyed() {
        super.onActivityDestroyed()
        if (behaviorSubject.hasObservers()) {
            behaviorSubject.onComplete()
        }
    }
}