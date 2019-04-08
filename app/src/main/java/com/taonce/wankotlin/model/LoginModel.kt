package com.taonce.wankotlin.model

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.contract.ILoginModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers


/**
 * Author: taoyongxiang
 * Date: 2019/4/8
 * Project: WanKotlin
 * Desc:
 * Copyright (C) 2019 Aulton. All rights reserved.
 */

class LoginModel : ILoginModel {
    override fun getRegister(
        username: String,
        password: String,
        rePassword: String,
        listener: ILoginModel.OnGetRegisterListener
    ) {
        RetrofitUtil.mInstance
            .getService()
            .register(username, password, rePassword)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BaseBean> {
                override fun onSuccess(value: BaseBean) {
                    listener.onGetRegister(value)
                }

                override fun onFailed() {
                    listener.onGetRegister(null)
                }
            })
    }

    override fun getLogin(
        username: String,
        password: String,
        listener: ILoginModel.OnGetLoginListener
    ) {
        RetrofitUtil.mInstance
            .getService()
            .login(username, password)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<LoginBean> {
                override fun onSuccess(value: LoginBean) {
                    listener.onGetLogin(value)
                }

                override fun onFailed() {
                    listener.onGetLogin(null)
                }
            })
    }
}