package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.contract.IHotKeyModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class HotKeyModel : IHotKeyModel {
    override fun getHotKey(listener: IHotKeyModel.OnGetHotKeyListener) {
        RetrofitUtil.mInstance
            .getService()
            .getHotKey()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<HotKeyBean> {
                override fun onSuccess(value: HotKeyBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }
}