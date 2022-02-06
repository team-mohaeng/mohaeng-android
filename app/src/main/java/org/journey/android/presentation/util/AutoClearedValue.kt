package org.journey.android.presentation.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedValue<T: Any>: ReadWriteProperty<Fragment, T>, LifecycleObserver {
    private var _value:T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        _value ?: throw  IllegalStateException("AutoClearedValue is not available")

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        thisRef.viewLifecycleOwner.lifecycle.removeObserver(this)
        _value = value
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        _value = null
    }
}