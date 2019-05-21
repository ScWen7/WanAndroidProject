package com.scwen.wanandroid

import com.scwen.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initActivity() {
        initImmBar()

    }

    override fun initData() {
    }


}
