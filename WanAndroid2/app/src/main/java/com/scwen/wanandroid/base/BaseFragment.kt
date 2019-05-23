package com.scwen.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
abstract class BaseFragment : Fragment(), BaseView {

    private var isViewCreate: Boolean = false

    private var isVisvable: Boolean = false

    protected var isLazy = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater.inflate(getLayoutRes(), container, false)
        val parent = contentView?.parent
        if (parent != null) {
            (parent as ViewGroup).removeView(contentView)
        }
        isViewCreate = true
        return contentView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        if (isLazy) {
            lazyLoad()
        } else {
            loadData()
        }
    }

    open fun initView() {}

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isLazy) {
            isVisvable = isVisibleToUser
            lazyLoad()
        }
    }


    fun lazyLoad() {
        if (isVisvable && isViewCreate) {
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreate = false;
            isVisvable = false;
            loadData()
        }
    }

    abstract fun loadData()

    abstract fun getLayoutRes(): Int

    override fun showLoading(str: CharSequence) {

    }

    override fun hideLoading() {
    }

    override fun showCommonMag(str: CharSequence) {
    }

    override fun showErrorMag(str: CharSequence) {
    }

    override fun showSuccessMag(str: CharSequence) {
    }

}