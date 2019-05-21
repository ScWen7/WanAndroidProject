package com.scwen.wanandroid.base

import android.text.method.TextKeyListener.clear
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper.dispose

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
abstract class BaseModel {
    var mDisposables: CompositeDisposable? = null

    fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            if (mDisposables == null) {
                mDisposables = CompositeDisposable()
            }
            mDisposables?.add(it)
        }
    }

    fun onDetch() {
        mDisposables?.let {
            it.dispose()
            it.clear()
        }
    }
}