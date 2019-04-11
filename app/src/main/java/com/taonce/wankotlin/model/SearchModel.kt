package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.contract.ISearchModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers

class SearchModel:ISearchModel{
    override fun getSearch(index: Int, key: String, listener: ISearchModel.OnGetSearchListener) {
        RetrofitUtil.mInstance.getService()
            .query(index,key)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<QueryBean> {
                override fun onSuccess(value: QueryBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }
}