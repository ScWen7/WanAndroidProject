package com.scwen.wanandroid.base

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by scwen on 2019/5/23.
 *  QQ ：811733738
 *  作用：
 */
class App :Application() {


    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}