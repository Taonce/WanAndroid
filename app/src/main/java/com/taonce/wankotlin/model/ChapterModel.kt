package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.ChapterBean
import com.taonce.wankotlin.contract.IChapterModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */
class ChapterModel : IChapterModel {
    override fun getWxChapter(listener: IChapterModel.OnGetChapterListener) {
        RetrofitUtil.mInstance
            .getService()
            .wxChapters()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<ChapterBean> {
                override fun onSuccess(value: ChapterBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }
}
