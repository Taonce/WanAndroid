package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.ArticleBean
import com.taonce.wankotlin.bean.ChapterBean
import com.taonce.wankotlin.bean.TreeBean
import com.taonce.wankotlin.bean.WxHistoryBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

interface IMainView {

    fun showArticle(articleBean: ArticleBean)

    fun showWxList(wxHistoryBean: WxHistoryBean)
}

interface IMainModel {

    fun getArticle(index: Int, cid: String, listener: OnGetArticleListener)

    fun getWxList(id: String, index: Int, listener: OnGetWxListListener)



    interface OnGetArticleListener {
        fun onListener(articleBean: ArticleBean)
    }

    interface OnGetWxListListener {
        fun onListener(wxHistoryBean: WxHistoryBean)
    }
}