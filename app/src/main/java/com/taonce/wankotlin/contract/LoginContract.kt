package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.ui.inter.IBaseView


/**
 * Author: taoyongxiang
 * Date: 2019/4/8
 * Project: WanKotlin
 * Desc:
 */

interface ILoginView : IBaseView {
    fun showLoginResult(bean: LoginBean)

    fun showRegisterResult(bean: LoginBean)
}

interface ILoginModel {
    fun getLogin(username: String, password: String, listener: OnGetLoginListener)

    interface OnGetLoginListener {
        fun onGetLogin(bean: LoginBean?)
    }

    fun getRegister(username: String, password: String, rePassword: String, listener: OnGetRegisterListener)

    interface OnGetRegisterListener {
        fun onGetRegister(bean: LoginBean?)
    }
}