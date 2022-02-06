package org.journey.android.domain.usecase

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class UseCase {
    protected var lastDisposable: Disposable? = null
    protected val compositeDisposable = CompositeDisposable()

    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
    fun dispose() {
        compositeDisposable.clear()
    }
}