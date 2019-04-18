package com.taonce.wankotlin.contract

import com.taonce.wankotlin.base.IBaseView
import com.taonce.wankotlin.bean.CollectionListBean


interface ICollectionListView : IBaseView {
    fun showCollectionList(collectionListBean: MutableList<CollectionListBean.Data.DatasItem>)

    fun showNull()
}

interface ICollectionListModel {

    fun getCollectionListData(index: Int, listener: OnGetCollectionListDataListener)

    interface OnGetCollectionListDataListener {
        fun onListener(collectionListBean: CollectionListBean?)
    }
}

