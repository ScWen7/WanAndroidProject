package com.scwen.wanandroid

import com.scwen.wanandroid.base.BaseActivity
import com.scwen.wanandroid.net.HttpClient
import com.scwen.wanandroid.net.excute
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initActivity() {
        initImmBar()

        HttpClient.api.getBanners().excute(onSuccess = {
        })
    }

    override fun initData() {
    }


}
