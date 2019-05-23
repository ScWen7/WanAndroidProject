package com.scwen.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    abstract fun getLayoutRes(): Int

    var mImmersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initActivity()
        initData()
    }

    abstract fun initActivity()

    abstract fun initData()

    /**
     * 初始化 状态栏相关
     */
    open fun initImmBar() {
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.let {
            it.statusBarDarkFont(false, 0.4f)
            it.init()
        }
    }

    override fun onDestroy() {

        super.onDestroy()
    }

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