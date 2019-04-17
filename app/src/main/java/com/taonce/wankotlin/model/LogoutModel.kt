package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.contract.ILoginModel
import com.taonce.wankotlin.contract.ILogoutModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers


class LogoutModel : ILogoutModel {
    override fun getLogout(listener: ILogoutModel.OnGetLogoutListener) {
        RetrofitUtil.mInstance
            .getService()
            .logout()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<LoginBean> {
                override fun onSuccess(value: LoginBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }
}