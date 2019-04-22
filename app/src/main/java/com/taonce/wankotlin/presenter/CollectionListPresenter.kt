package com.taonce.wankotlin.presenter

import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.CollectionListBean
import com.taonce.wankotlin.contract.IChapterArticleModel
import com.taonce.wankotlin.contract.ICollectionListModel
import com.taonce.wankotlin.contract.ICollectionListView
import com.taonce.wankotlin.model.CollectionListModel


class CollectionListPresenter(private val mView: ICollectionListView) :
    BasePresenter<ICollectionListView>(),
    ICollectionListModel.OnGetCollectionListDataListener,
    ICollectionListModel.OnGetCancelCollectionListener {

    private val model: ICollectionListModel by lazy { CollectionListModel() }

    fun getCollectionList(index: Int) {
        mView.showLoading()
        model.getCollectionListData(index, this)
    }

    fun cancelCollection(id: Int, originId: Int) {
        mView.showLoading()
        model.cancelCollection(id, originId, this)
    }

    override fun onListener(collectionListBean: CollectionListBean?) {
        mView.hideLoading()
        collectionListBean ?: mView.showNull()
        collectionListBean?.let { bean ->
            bean.data.datas ?: mView.showNull()
            bean.data.datas?.let { mView.showCollectionList(it) }
        }
    }

    override fun onListener(baseBean: BaseBean?) {
        mView.hideLoading()
        baseBean?.let {
            mView.showCancel(it)
            App.mInstance.toast("取消收藏")
        }
    }
}