package com.taonce.wankotlin.presenter

import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.contract.ISearchModel
import com.taonce.wankotlin.contract.ISearchView
import com.taonce.wankotlin.model.SearchModel

class SearchPresenter(private val mView: ISearchView) : BasePresenter<ISearchView>()
    , ISearchModel.OnGetSearchListener {

    private val model: ISearchModel = SearchModel()

    fun getSearch(index: Int, key: String) {
        if (key.isNotEmpty()) {
            model.getSearch(
                index, key, this
            )
            mView.showLoading()
        }
    }

    override fun onListener(queryBean: QueryBean?) {
        mView.hideLoading()
        mView.refreshFinished()
        queryBean?.data?.datas?.let {
            if (it.isNotEmpty()) {
                mView.showSearchData(it)
            }else{
                App.mInstance.toast("No More! ")
            }
        }
    }
}