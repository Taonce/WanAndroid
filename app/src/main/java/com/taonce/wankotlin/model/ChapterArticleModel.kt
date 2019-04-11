package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.WxHistoryBean
import com.taonce.wankotlin.contract.IChapterArticleModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */
class ChapterArticleModel : IChapterArticleModel {

    override fun getWxHistory(id: String, index: Int, listener: IChapterArticleModel.OnGetWxHistoryListener) {
        RetrofitUtil.mInstance
            .getService()
            .wxHistory(id, index)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<WxHistoryBean> {
                override fun onSuccess(value: WxHistoryBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }
}

