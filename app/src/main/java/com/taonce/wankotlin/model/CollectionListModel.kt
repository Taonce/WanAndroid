package com.taonce.wankotlin.model

import com.taonce.utilmodule.formatDate2Day
import com.taonce.utilmodule.showDebug
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.bean.CollectionListBean
import com.taonce.wankotlin.bean.db.CollectionDB
import com.taonce.wankotlin.contract.ICollectionListModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.litepal.LitePal
import org.litepal.extension.deleteAllAsync
import java.util.zip.Adler32

class CollectionListModel : ICollectionListModel {
    private val dbList: MutableList<CollectionDB> = mutableListOf()
    override fun getCollectionListData(index: Int, listener: ICollectionListModel.OnGetCollectionListDataListener) {
        RetrofitUtil.mInstance.getService()
            .getCollectionList()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<CollectionListBean> {
                override fun onSuccess(value: CollectionListBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }

    override fun getDB(): MutableList<CollectionDB> {
        val list: MutableList<CollectionDB> = LitePal.findAll(CollectionDB::class.java)
        list.forEach {
            it.id = 0
        }
        return list
    }

    override fun cancelCollection(
        id: Int,
        originId: Int,
        listener: ICollectionListModel.OnGetCancelCollectionListener
    ) {
        RetrofitUtil.mInstance
            .getService()
            .cancelCollection(id, originId)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BaseBean> {
                override fun onSuccess(value: BaseBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }

    override fun deleteCollect4DB(
        id: Int,
        originId: Int,
        listener: ICollectionListModel.OnGetCancelCollectionListener
    ) {
        LitePal.deleteAllAsync<CollectionDB>("articleId = ? ", id.toString()).listen {
            val baseBean = BaseBean()
            baseBean.errorMsg = ""
            baseBean.errorCode = 0
            listener.onListener(baseBean)
        }
    }
}

