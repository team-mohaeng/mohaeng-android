package org.journey.android.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class DisposableViewModel: ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}


