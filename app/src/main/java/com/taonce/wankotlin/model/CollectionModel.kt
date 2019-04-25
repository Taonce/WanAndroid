package com.taonce.wankotlin.model

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.bean.db.CollectionDB
import com.taonce.wankotlin.contract.ICollectionModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers
import org.litepal.LitePal
import org.litepal.extension.find
import org.litepal.extension.findAll


class CollectionModel : ICollectionModel {

    override fun collection(id: Int, listener: ICollectionModel.OnCollectionListener) {
        RetrofitUtil.mInstance
            .getService()
            .collectionInner(id)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BaseBean> {
                override fun onSuccess(value: BaseBean) {
                    listener.onCollectionListener(value)
                }

                override fun onFailed() {
                    listener.onCollectionListener(null)
                }
            })
    }

    override fun unCollection(id: Int, listener: ICollectionModel.OnUnCollectionListener) {
        RetrofitUtil.mInstance
            .getService()
            .unCollection(id)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BaseBean> {
                override fun onSuccess(value: BaseBean) {
                    listener.onUnCollectionListener(value)
                }

                override fun onFailed() {
                    listener.onUnCollectionListener(null)
                }
            })
    }

    override fun collect2DB(
        id: Int,
        url: String,
        collectTime: String,
        publishTime: String,
        author: String,
        title: String,
        listener: ICollectionModel.OnCollect2DBListener
    ) {
        val collectDBList: List<CollectionDB> = LitePal.where("url = ? ", url).find()
        if (collectDBList.isEmpty()) {
            val collectDB = CollectionDB()
            collectDB.run {
                articleId = id
                this.url = url
                this.collectTime = collectTime
                this.publishTime = publishTime
                this.author = author
                this.title = title
                save()
            }
            listener.onCollect2DBListener()
        } else {
            listener.onCollect2DBFailedListener()
        }
    }
}

