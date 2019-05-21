package com.scwen.wanandroid.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
abstract class BasePresenter<M : BaseModel, V : BaseView> {
    protected var mModel: M? = null
    protected var mView: V? = null

    var mDisposables: CompositeDisposable? = null

    private var isViewAttached: Boolean = false
        get() = mView != null

    constructor(view:V){
        mView = view
        mModel = createModel()
    }

    open fun createModel(): M? = null

    open fun detachView() {
        clearDisposable()
        mModel?.onDetch()
        mModel = null
        mView = null
        mDisposables = null
    }


    fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            if (mDisposables == null) {
                mDisposables = CompositeDisposable()
            }
            mDisposables?.add(it)
        }
    }

    fun clearDisposable() {
        mDisposables?.let {
            it.dispose()
            it.clear()
        }
        mDisposables = null
    }

}