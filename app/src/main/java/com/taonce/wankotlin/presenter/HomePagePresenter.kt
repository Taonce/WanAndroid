package com.taonce.wankotlin.presenter

import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.BannerBean
import com.taonce.wankotlin.bean.HomePageBean
import com.taonce.wankotlin.contract.IHomePageModel
import com.taonce.wankotlin.contract.IHomePageView
import com.taonce.wankotlin.model.HomePageModel

class HomePagePresenter(private val mView: IHomePageView) : BasePresenter<IHomePageView>(),
    IHomePageModel.OnGetBannerListener,
    IHomePageModel.OnGetHomePageListener {

    private val model: IHomePageModel = HomePageModel()

    fun getHomePageData(index: Int) = model.getHomePageData(index, this)

    fun getBanner() = model.getBanner(this)

    override fun onListener(homePageBean: HomePageBean?) {
        mView.hideLoadingView()
        homePageBean?.let { mView.showHomePageData(it) }
    }

    override fun onListener(bannerBean: BannerBean?) {
        bannerBean?.let { mView.showBanner(it) }
    }
}