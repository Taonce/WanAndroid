package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.TreeBean
import com.taonce.wankotlin.contract.ITreeModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class TreeModel : ITreeModel {

    override fun getTreeSystem(listener: ITreeModel.OnGetTreeListener) {
        RetrofitUtil.mInstance
            .getService()
            .getTree()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<TreeBean>{
                override fun onSuccess(value: TreeBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }
}