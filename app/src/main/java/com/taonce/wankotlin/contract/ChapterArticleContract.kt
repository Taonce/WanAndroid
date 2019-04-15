package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.WxHistoryBean
import com.taonce.wankotlin.base.IBaseView


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */
interface IChapterArticleView : IBaseView {
    fun showWxHistory(wxHistoryBean: MutableList<WxHistoryBean.Data.DatasItem>)

    fun refreshFinished()
}

interface IChapterArticleModel {

    fun getWxHistory(id: String, index: Int, listener: OnGetWxHistoryListener)

    interface OnGetWxHistoryListener {
        fun onListener(wxHistoryBean: WxHistoryBean?)
    }
}
