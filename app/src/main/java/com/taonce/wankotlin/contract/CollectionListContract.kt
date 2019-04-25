package com.taonce.wankotlin.contract

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.IBaseView
import com.taonce.wankotlin.bean.CollectionListBean
import com.taonce.wankotlin.bean.db.CollectionDB


interface ICollectionListView : IBaseView {
    fun showCollectionList(collectionBean: MutableList<CollectionDB>)

    fun showNull()

    fun showCancel(baseBean: BaseBean)
}

interface ICollectionListModel {

    fun getCollectionListData(index: Int, listener: OnGetCollectionListDataListener)

    fun cancelCollection(id: Int, originId: Int, listener: OnGetCancelCollectionListener)

    fun getDB(): MutableList<CollectionDB>

    fun deleteCollect4DB(id: Int, originId: Int, listener: OnGetCancelCollectionListener)

    interface OnGetCollectionListDataListener {
        fun onListener(collectionListBean: CollectionListBean?)
    }

    interface OnGetCancelCollectionListener {
        fun onListener(baseBean: BaseBean?)
    }

}

