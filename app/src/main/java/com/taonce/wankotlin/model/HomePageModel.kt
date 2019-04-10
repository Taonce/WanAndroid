package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.BannerBean
import com.taonce.wankotlin.bean.HomePageBean
import com.taonce.wankotlin.contract.IHomePageModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers

class HomePageModel : IHomePageModel {

    override fun getHomePageData(index: Int, listener: IHomePageModel.OnGetHomePageListener) {
        RetrofitUtil.mInstance
            .getService()
            .getHomePageArticle(index)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<HomePageBean> {
                override fun onSuccess(value: HomePageBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }

    override fun getBanner(listener: IHomePageModel.OnGetBannerListener) {
        RetrofitUtil.mInstance
            .getService()
            .getBanner()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BannerBean> {
                override fun onSuccess(value: BannerBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }
}