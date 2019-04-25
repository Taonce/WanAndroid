package com.taonce.wankotlin.presenter

import com.taonce.utilmodule.formatDate2Day
import com.taonce.utilmodule.showDebug
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.CollectionListBean
import com.taonce.wankotlin.bean.db.CollectionDB
import com.taonce.wankotlin.contract.IChapterArticleModel
import com.taonce.wankotlin.contract.ICollectionListModel
import com.taonce.wankotlin.contract.ICollectionListView
import com.taonce.wankotlin.model.CollectionListModel
import com.taonce.wankotlin.net.RxSchedulers
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


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
        // originId 为-1，说明收藏在本地
        if (originId == -1) {
            model.deleteCollect4DB(id, originId, this)
        } else {
            model.cancelCollection(id, originId, this)
        }
    }

    override fun onListener(collectionListBean: CollectionListBean?) {
        mView.hideLoading()
        val result: MutableList<CollectionDB> = mutableListOf()
        collectionListBean ?: showDB(model.getDB())
        collectionListBean?.let { bean ->
            bean.data.datas ?: showDB(model.getDB())
            bean.data.datas?.let { data ->
                val networkData: Observable<CollectionDB> = Observable.fromIterable(data)
                    .flatMap {
                        return@flatMap Observable.just(map2DB(it))
                    }

                val dbData: Observable<CollectionDB> = Observable.fromIterable(model.getDB())
                Observable.concat(networkData, dbData)
                    .compose(RxSchedulers.observableTransformer())
                    .map {
                        result.add(it)
                    }
                    .subscribe({}, {}, {
                        if (result.isEmpty()) {
                            mView.showNull()
                        } else {
                            mView.showCollectionList(result)
                        }
                    })
            }
        }
    }

    private fun showDB(list: MutableList<CollectionDB>) {
        if (list.isEmpty()) {
            mView.showNull()
        } else {
            mView.showCollectionList(list)
        }
    }

    private fun map2DB(data: CollectionListBean.Data.DatasItem): CollectionDB {
        data.let {
            val db = CollectionDB()
            db.author = it.author
            db.collectTime = it.niceDate
            db.articleId = it.id
            db.publishTime = it.publishTime.formatDate2Day()
            db.title = it.title
            db.tag = 0
            db.url = it.link
            db.originId = it.originId
            return db
        }
    }

    /**
     * 取消收藏回调
     */
    override fun onListener(baseBean: BaseBean?) {
        mView.hideLoading()
        baseBean?.let {
            mView.showCancel(it)
            App.mInstance.toast("取消收藏")
        }
    }
}