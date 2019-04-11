package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.WxHistoryBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */
interface IChapterArticleView {
    fun showWxHistory(wxHistoryBean: WxHistoryBean)
}

interface IChapterArticleModel {

    fun getWxHistory(id: String, index: Int, listener: OnGetWxHistoryListener)

    interface OnGetWxHistoryListener {
        fun onListener(wxHistoryBean: WxHistoryBean?)
    }
}
