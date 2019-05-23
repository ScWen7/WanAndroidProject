package com.scwen.wanandroid.home

import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwen.wanandroid.R
import com.scwen.wanandroid.base.BaseRefreshFragment
import com.scwen.wanandroid.home.model.BannerBean
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

    override fun initView() {
        super.initView()

        bannerView = banner_home
        bannerView?.setAdapter(object : BGABanner.Adapter<ImageView, String> {
            override fun fillBannerItem(banner: BGABanner?, itemView: ImageView?, model: String?, position: Int) {
                itemView?.let {
                    Glide.with(this@HomeFragment)
                        .load(model)
                        .into(it)
                }
            }

        })
    }


    override fun loadData() {
        mPresenter?.getBanners()
    }

    override fun requestData() {

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
            it.setData(imagesList,titleList)
            it.setAutoPlayAble(imagesList.size>1)
        }

    }
}