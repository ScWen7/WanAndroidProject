package com.scwen.wanandroid.weight

import com.google.android.material.appbar.AppBarLayout

/**
 * Created by scwen on 2019/5/27.
 *  QQ ：811733738
 *  作用：
 */
abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {


    enum class State {
        //        展开状态
        EXPANDED,
        //折叠状态
        COLLAPSED,
        //中间状态
        IDLE
    }


    var mCurrentState: State = State.IDLE


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, i: Int) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED)
            }
            mCurrentState = State.EXPANDED
        } else if (Math.abs(i) >= (appBarLayout?.getTotalScrollRange() ?: 0)) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED)
            }
            mCurrentState = State.COLLAPSED
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE)
            }
            mCurrentState = State.IDLE
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout?, state: State)


}