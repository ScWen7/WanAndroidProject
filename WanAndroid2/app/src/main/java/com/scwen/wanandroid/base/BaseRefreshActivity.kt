package com.scwen.wanandroid.base

import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.scwen.wanandroid.constant.Constant

/**
 * Created by scwen on 2019/5/23.
 *  QQ ：811733738
 *  作用：
 */
abstract class BaseRefreshActivity<V : BaseView, P : IPresenter<V>> : BaseMvpActivity<V, P>() {

    protected var mRefreshView: RecyclerView? = null

    protected var mRefreshLayout: SmartRefreshLayout? = null

    abstract fun initRefreshView(): RecyclerView

    abstract fun initRefreshLayout(): SmartRefreshLayout


    private var what: Int = Constant.REFRESH

    protected var page = 1

    protected var pageSize = 20


    override fun initActivity() {
        super.initActivity()
        mRefreshView = initRefreshView()
        mRefreshLayout = initRefreshLayout()
        initRefresh()

    }

    private fun initRefresh() {
        mRefreshLayout?.apply {
            setEnableLoadMoreWhenContentNotFull(false)
            setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    startLoadmore()
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    startRefresh()
                }
            })
        }
    }

    private fun startRefresh() {
        if (isFinishing) return
        what = Constant.REFRESH
        page = 1
        requestData()
    }

    private fun startLoadmore() {
        if (isFinishing) return
        what = Constant.LOADMORE
        page++
        requestData()
    }


    fun successAfter(dataLength: Int) {
        mRefreshLayout?.apply {
            if (what == Constant.LOADMORE) {
                if (dataLength < pageSize) {
                    finishLoadMoreWithNoMoreData()
                } else {
                    finishLoadMore()
                }
            } else {
                finishRefresh()
            }

        }
    }

    fun failAfter(){
        mRefreshLayout?.apply {
            if(what ==Constant.LOADMORE){
                finishLoadMore(false)
                page--
            }else{
                finishRefresh(false)
            }
        }
    }

    abstract fun requestData()
}