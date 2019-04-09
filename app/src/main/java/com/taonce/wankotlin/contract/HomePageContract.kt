package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.BannerBean
import com.taonce.wankotlin.bean.HomePageBean

interface IHomePageView {
    fun showHomePageData(homePageBean: HomePageBean)
    fun showBanner(bannerBean: BannerBean)
}

interface IHomePageModel {
    fun getHomePageData(index: Int,listener: OnGetHomePageListener)

    fun getBanner(listener: OnGetBannerListener)

    interface OnGetHomePageListener {
        fun onListener(homePageBean: HomePageBean?)
    }

    interface OnGetBannerListener {
        fun onListener(bannerBean: BannerBean?)
    }
}