package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.base.IBaseView

interface ISearchView : IBaseView {
    fun showSearchData(queryBean: MutableList<QueryBean.Data.DatasItem>)

    fun refreshFinished()
}

interface ISearchModel {

    fun getSearch(index: Int, key: String, listener: OnGetSearchListener)
    interface OnGetSearchListener {
        fun onListener(queryBean: QueryBean?)
    }
}