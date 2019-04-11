package com.taonce.wankotlin.presenter

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

    fun getWxHistory(id: String, index: Int): Unit = mModel.getWxHistory(id, index, this)

    override fun onListener(wxHistoryBean: WxHistoryBean?) {
        wxHistoryBean?.let { mView.showWxHistory(it) }
    }
}