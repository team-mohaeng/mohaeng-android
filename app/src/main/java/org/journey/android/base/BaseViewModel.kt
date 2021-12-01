package org.journey.android.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel(){
    private val compositeDisposable = CompositeDisposable()
    fun addDisposable(d: Disposable){
        compositeDisposable.add(d)
    }
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}