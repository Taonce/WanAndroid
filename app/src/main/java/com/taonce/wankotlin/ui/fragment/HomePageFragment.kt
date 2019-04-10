package com.taonce.wankotlin.ui.fragment

import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseFragment
import com.taonce.wankotlin.bean.BannerBean
import com.taonce.wankotlin.bean.HomePageBean
import com.taonce.wankotlin.contract.IHomePageView
import com.taonce.wankotlin.ui.adapter.HomeViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : BaseFragment(), IHomePageView {

    private var mUrl: MutableList<String> = mutableListOf()
    private var mViewPagerAdapter: HomeViewPagerAdapter? = null

    private var mData: MutableList<HomePageBean.Data.DatasItem> = mutableListOf()

    override fun getLayoutId(): Int = R.layout.fragment_home_page

    override fun initData() {
    }

    override fun initView() {
        mViewPagerAdapter = HomeViewPagerAdapter(this.context!!, mUrl)
        vp_home.adapter = mViewPagerAdapter
        vp_home.pageMargin = 16
    }

    override fun initEvent() {
    }

    override fun showHomePageData(homePageBean: HomePageBean) {
        homePageBean.data.datas?.let {
            mData.addAll(0, it)
        }
    }

    override fun showBanner(bannerBean: BannerBean) {
        bannerBean.data.let {
            it.forEach { url ->
                mUrl.add(url.url)
            }
            mViewPagerAdapter = HomeViewPagerAdapter(this.context!!, mUrl)
            mViewPagerAdapter?.notifyDataSetChanged()
        }
    }
}