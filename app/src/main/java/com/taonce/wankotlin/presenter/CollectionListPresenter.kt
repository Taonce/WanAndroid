package com.taonce.wankotlin.presenter

import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.CollectionListBean
import com.taonce.wankotlin.contract.IChapterArticleModel
import com.taonce.wankotlin.contract.ICollectionListModel
import com.taonce.wankotlin.contract.ICollectionListView
import com.taonce.wankotlin.model.CollectionListModel


class CollectionListPresenter(private val mView: ICollectionListView) :
    BasePresenter<ICollectionListView>(),
    ICollectionListModel.OnGetCollectionListDataListener {

    private val model: ICollectionListModel by lazy { CollectionListModel() }

    fun getCollectionList(index: Int) {
        mView.showLoading()
        model.getCollectionListData(index, this)
    }

    override fun onListener(collectionListBean: CollectionListBean?) {
        mView.hideLoading()
        collectionListBean ?: mView.showNull()
        collectionListBean?.let { bean ->
            bean.data.datas ?: mView.showNull()
            bean.data.datas?.let { mView.showCollectionList(it) }
        }
    }
}