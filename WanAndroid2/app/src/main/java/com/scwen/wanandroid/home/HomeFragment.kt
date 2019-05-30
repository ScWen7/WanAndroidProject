package com.scwen.wanandroid.home

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.scwen.wanandroid.R
import com.scwen.wanandroid.base.App
import com.scwen.wanandroid.base.BaseRefreshFragment
import com.scwen.wanandroid.constant.Constant
import com.scwen.wanandroid.home.adapter.HomeAdapter
import com.scwen.wanandroid.home.model.Article
import com.scwen.wanandroid.home.model.BannerBean
import com.scwen.wanandroid.weight.DividerDecoration
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
class HomeFragment : BaseRefreshFragment<HomeView, HomePresenter>(), HomeView {


    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun initRefreshView(): RecyclerView = recycler_home

    override fun initRefreshLayout(): SmartRefreshLayout = refresh_home

    override fun createPresenter(): HomePresenter = HomePresenter()

    var bannerView: BGABanner? = null

    var bannerData: List<BannerBean>? = null

    var homeAdapter: HomeAdapter? = null

    override fun initView() {
        super.initView()

        bannerView = LayoutInflater.from(activity)?.inflate(R.layout.item_banner_home, refresh_home, false) as BGABanner
        bannerView?.setAdapter(object : BGABanner.Adapter<ImageView, String> {
            override fun fillBannerItem(banner: BGABanner?, itemView: ImageView?, model: String?, position: Int) {
                itemView?.let {
                    Glide.with(this@HomeFragment)
                        .load(model)
                        .into(it)
                }
            }
        })

        initRecycler()
    }

    private fun initRecycler() {
        homeAdapter = HomeAdapter(null)
        mRefreshView?.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.addItemDecoration(
                DividerDecoration(
                    ContextCompat.getColor(App.context, R.color.text_999),
                    DensityUtil.dp2px(0.5f),
                    0,
                    0
                )
            )
            it.adapter = homeAdapter
        }
        homeAdapter?.let {
            it.addHeaderView(bannerView)
        }
    }


    override fun loadData() {
        mPresenter?.getBanners()
        mPresenter?.getHomeData()
    }

    override fun requestData() {
        mPresenter?.getArticles(page)
    }

    override fun showBanners(banners: List<BannerBean>) {
        bannerData = banners
        var imagesList = ArrayList<String>()
        var titleList = ArrayList<String>()
        banners?.forEach {
            imagesList.add(it.imagePath)
            titleList.add(it.title)
        }

        bannerView?.let {
            it.setData(imagesList, titleList)
            it.setAutoPlayAble(imagesList.size > 1)
        }

    }

    override fun loadFail(msg: String) {
        if (what == Constant.REFRESH) {
            showErrorMag(msg)
        }
        failAfter()
    }

    override fun showArticles(articles: MutableList<Article>) {
        if (articles?.size > 0) {
            if (what == Constant.REFRESH) {
                homeAdapter?.setNewData(articles)
            } else {
                homeAdapter?.addData(articles)
            }
        }
        successAfter(articles?.size)
    }
}