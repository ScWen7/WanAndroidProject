package com.scwen.wanandroid

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.scwen.wanandroid.base.BaseActivity
import com.scwen.wanandroid.delegate.Preference
import com.scwen.wanandroid.find.FindFragment
import com.scwen.wanandroid.home.HomeFragment
import com.scwen.wanandroid.mine.MineFragment
import com.scwen.wanandroid.navigation.NavigationFragment
import com.scwen.wanandroid.net.HttpClient
import com.scwen.wanandroid.net.excute
import com.scwen.wanandroid.system.SystemFragment
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_main


    private val container = R.id.fl_container


    private val HOME_TAG: String = "home"
    private val FIND_TAG: String = "find"
    private val SYSTEM_TAG: String = "system"
    private val NAVIGATION_TAG: String = "navigation"
    private val MINE_TAG: String = "mine"

    private val FRAGMENT_HOME = 0x01
    private val FRAGMENT_FIND = 0x02
    private val FRAGMENT_SYSTEM = 0x03
    private val FRAGMENT_NAVIGATION = 0x04
    private val FRAGMENT_MINE = 0x05

    private var mIndex = 0


    private var homeFragment: HomeFragment? = null
    private var findFragment: FindFragment? = null
    private var systemFragment: SystemFragment? = null
    private var navigationFragment: NavigationFragment? = null
    private var mineFragment: MineFragment? = null


    override fun initActivity() {
        initImmBar()

        navigation_main.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.navigation_home -> {
                    showFragment(FRAGMENT_HOME)
                    true
                }
                R.id.navigation_find -> {
                    showFragment(FRAGMENT_FIND)
                    true
                }
                R.id.navigation_system -> {
                    showFragment(FRAGMENT_SYSTEM)
                    true
                }
                R.id.navigation_navigation -> {
                    showFragment(FRAGMENT_NAVIGATION)
                    true
                }
                R.id.navigation_account -> {
                    showFragment(FRAGMENT_MINE)
                    true
                }
                else -> false
            }
        }

    }

    fun showFragment(index: Int) {
        if (mIndex == index) return
        mIndex = index
        val transaction = supportFragmentManager.beginTransaction()

        hideFragment(transaction)

        when (index) {
            FRAGMENT_HOME -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    transaction.add(container, homeFragment!!, HOME_TAG)
                } else {
                    transaction.show(homeFragment!!)
                }
            }
            FRAGMENT_FIND -> {
                if (findFragment == null) {
                    findFragment = FindFragment()
                    transaction.add(container, findFragment!!, FIND_TAG)
                } else {
                    transaction.show(findFragment!!)
                }
            }
            FRAGMENT_SYSTEM -> {
                if (systemFragment == null) {
                    systemFragment = SystemFragment()
                    transaction.add(container, systemFragment!!, SYSTEM_TAG)
                } else {
                    transaction.show(systemFragment!!)
                }
            }
            FRAGMENT_NAVIGATION -> {
                if (navigationFragment == null) {
                    navigationFragment = NavigationFragment()
                    transaction.add(container, navigationFragment!!, NAVIGATION_TAG)
                } else {
                    transaction.show(navigationFragment!!)
                }
            }
            FRAGMENT_MINE -> {
                if (mineFragment == null) {
                    mineFragment = MineFragment()
                    transaction.add(container, mineFragment!!, MINE_TAG)
                } else {
                    transaction.show(mineFragment!!)
                }
            }
        }
        transaction.commit()

    }

    fun hideFragment(transaction: FragmentTransaction) {
        homeFragment?.let { transaction.hide(it) }
        findFragment?.let { transaction.hide(it) }
        systemFragment?.let { transaction.hide(it) }
        navigationFragment?.let { transaction.hide(it) }
        mineFragment?.let { transaction.hide(it) }
    }

    override fun initData() {
        showFragment(FRAGMENT_HOME)
    }


}
