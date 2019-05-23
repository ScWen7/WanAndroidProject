package com.scwen.wanandroid.delegate

import android.content.Context
import android.content.SharedPreferences
import com.scwen.wanandroid.base.App
import com.scwen.wanandroid.constant.Constant
import kotlin.reflect.KProperty

/**
 * Created by scwen on 2019/5/23.
 *  QQ ：811733738
 *  作用：
 */
class Preference<T>(val key: String, val default: T) {


    companion object {

        private val pres: SharedPreferences by lazy {
            App.context.getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE)
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharedPreferences(key, default)
    }

    private fun getSharedPreferences(key: String, default: T): T = with(pres) {
        var value = when (default) {
            is Long -> getLong(key, default)
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is String -> getString(key, default)
            else -> default
        }
        return@with value as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreferences(key, value)
    }

    private fun putPreferences(key: String, value: T) = with(pres.edit()) {
        when (default) {
            is Long -> putLong(key, default)
            is Int -> putInt(key, default)
            is Boolean -> putBoolean(key, default)
            is String -> putString(key, default)
            else -> putString(key, default.toString())
        }
        apply()
    }


}