package com.taonce.wankotlin.presenter

import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.WxHistoryBean
import com.taonce.wankotlin.contract.IChapterArticleModel
import com.taonce.wankotlin.contract.IChapterArticleView
import com.taonce.wankotlin.model.ChapterArticleModel
import com.taonce.wankotlin.model.ChapterModel


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */

class ChapterArticlePresenter(private val mView: IChapterArticleView) : BasePresenter<IChapterArticleView>()
    , IChapterArticleModel.OnGetWxHistoryListener {

    private val mModel: IChapterArticleModel = ChapterArticleModel()

    fun getWxHistory(id: String, index: Int) {
        mView.showLoading()
        mModel.getWxHistory(id, index, this)
    }

    override fun onListener(wxHistoryBean: WxHistoryBean?) {
        mView.hideLoading()
        mView.refreshFinished()
        wxHistoryBean?.data?.datas?.let {
            if (it.isNotEmpty())
                mView.showWxHistory(it)
            else {
                App.mInstance.toast(App.mInstance.getString(R.string.no_more))
            }
        }
    }
}