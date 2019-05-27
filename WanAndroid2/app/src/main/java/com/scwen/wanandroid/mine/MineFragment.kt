package com.scwen.wanandroid.mine

import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.scwen.wanandroid.R
import com.scwen.wanandroid.base.BaseFragment
import com.scwen.wanandroid.weight.AppBarStateChangeListener
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by scwen on 2019/5/24.
 *  QQ ：811733738
 *  作用：
 */
class MineFragment : BaseFragment() {

    override fun loadData() {
        smartrefreshlayout.setEnablePureScrollMode(true)//是否启用纯滚动模式
        smartrefreshlayout.setEnableOverScrollDrag(true)//是否启用越界拖动（仿苹果效果）1.0.4
        appbar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State) {
                when (state) {
                    State.COLLAPSED -> toolbar.visibility = View.VISIBLE
                    State.EXPANDED -> toolbar.visibility = View.INVISIBLE
                    else -> toolbar.visibility = View.INVISIBLE
                }
            }

        })
        ll_tool_content.setOnClickListener {
            activity?.let {
                LoginActivity.start(it)
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_mine
}