package com.taonce.wankotlin.model

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.bean.CollectionListBean
import com.taonce.wankotlin.contract.ICollectionListModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers

class CollectionListModel:ICollectionListModel{

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
}

