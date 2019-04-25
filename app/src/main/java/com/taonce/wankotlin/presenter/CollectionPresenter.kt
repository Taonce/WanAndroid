package com.taonce.wankotlin.presenter

import android.content.Context
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.contract.ICollectionModel
import com.taonce.wankotlin.contract.ICollectionView
import com.taonce.wankotlin.model.CollectionModel

class CollectionPresenter(private val mView: ICollectionView) :
    BasePresenter<ICollectionView>(),
    ICollectionModel.OnUnCollectionListener,
    ICollectionModel.OnCollectionListener,
    ICollectionModel.OnCollect2DBListener {

    private val model: ICollectionModel by lazy { CollectionModel() }
    private val mContext: Context by lazy { App.mInstance }

    fun collectionArticle(id: Int) {
        mView.showLoading()
        model.collection(id, this)
    }

    fun unCollectionArticle(id: Int) {
        mView.showLoading()
        model.unCollection(id, this)
    }

    fun collect2DB(
        id: Int,
        url: String,
        collectTime: String,
        publishTime: String,
        author: String,
        title: String
    ) {
        mView.showLoading()
        model.collect2DB(id, url, collectTime, publishTime, author, title,this)
    }

    override fun onCollectionListener(baseBean: BaseBean?) {
        mView.hideLoading()
        baseBean?.let {
            mContext.toast(R.string.collect_success)
            mView.collection(it)
        }
    }

    override fun onUnCollectionListener(baseBean: BaseBean?) {
        mView.hideLoading()
        baseBean?.let {
            mContext.toast(R.string.un_collect_success)
            mView.unCollection(it)
        }
    }

    override fun onCollect2DBListener() {
        mView.hideLoading()
        mContext.toast(R.string.collect_success)
        mView.collect2DB()
    }

    override fun onCollect2DBFailedListener() {
        mView.hideLoading()
        mContext.toast(R.string.collect_failed)
    }
}

