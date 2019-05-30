package com.scwen.wanandroid

import com.scwen.wanandroid.base.BaseActivity
import com.scwen.wanandroid.delegate.Preference
import com.scwen.wanandroid.home.HomeFragment
import com.scwen.wanandroid.mine.MineFragment
import com.scwen.wanandroid.net.HttpClient
import com.scwen.wanandroid.net.excute
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_main


    private val container = R.id.fl_container


    override fun initActivity() {
        initImmBar()

    }

    override fun initData() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(container,HomeFragment(),"home").commit()
    }


}
