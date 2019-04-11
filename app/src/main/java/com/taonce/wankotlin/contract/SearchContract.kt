package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.ui.inter.IBaseView

interface ISearchView : IBaseView {
    fun showSearchData(queryBean: QueryBean)
}

interface ISearchModel {

    fun getSearch(index: Int, key: String, listener: OnGetSearchListener)
    interface OnGetSearchListener {
        fun onListener(queryBean: QueryBean?)
    }
}