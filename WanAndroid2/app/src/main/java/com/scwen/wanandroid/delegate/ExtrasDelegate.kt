package com.scwen.wanandroid.delegate

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

/**
 * Created by scwen on 2019/5/23.
 *  QQ ：811733738
 *  作用：
 */
class ExtrasDelegate<T>(var extraName: String, var defaultValue: T) {

    private var extra: T? = null

    operator fun getValue(thisRef: AppCompatActivity?, property: KProperty<*>): T {
        extra = getValue(extra, extraName, thisRef)
        return extra ?: defaultValue
    }

    operator fun setValue(thisRef: AppCompatActivity?, property: KProperty<*>, value: T) {
        extra = value
    }

    operator fun getValue(thisRef: Fragment?, property: KProperty<*>): T {
        extra = getValue(extra, extraName, thisRef)
        return extra ?: defaultValue
    }

    operator fun setValue(thisRef: Fragment?, property: KProperty<*>, value: T) {
        extra = value
    }


    private fun getValue(oldExtra: T?, extraName: String, thisRef: AppCompatActivity?): T =
        oldExtra ?: thisRef?.intent?.extras?.get(extraName) as T

    private fun getValue(oldExtra: T?, extraName: String, thisRef: Fragment?): T =
        oldExtra ?: thisRef?.arguments?.get(extraName) as T
}

fun <T> extraDelegate(extraName: String, defaultValue: T) = ExtrasDelegate(extraName, defaultValue)

fun extraDelegate(extraName: String) = extraDelegate(extraName, null)