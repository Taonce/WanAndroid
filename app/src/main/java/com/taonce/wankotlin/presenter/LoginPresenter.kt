package com.taonce.wankotlin.presenter

import android.content.Context
import android.text.TextUtils
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.contract.ILoginModel
import com.taonce.wankotlin.contract.ILoginView
import com.taonce.wankotlin.model.LoginModel


/**
 * Author: taoyongxiang
 * Date: 2019/4/8
 * Project: WanKotlin
 * Desc:
 */

class LoginPresenter(private val view: ILoginView) :
    BasePresenter<ILoginView>(),
    ILoginModel.OnGetLoginListener,
    ILoginModel.OnGetRegisterListener {

    private val model = LoginModel()

    fun login(context: Context, userName: String, password: String) {
        when {
            TextUtils.isEmpty(userName) -> {
                context.toast("userName can not be empty! ")
                return
            }
            TextUtils.isEmpty(password) -> {
                context.toast("password can not be empty! ")
                return
            }
            else -> model.getLogin(userName, password, this)
        }
    }

    fun register(context: Context, userName: String, password: String, rePassword: String) {
        when {
            TextUtils.isEmpty(userName) -> {
                context.toast("userName can not be empty! ")
                return
            }
            TextUtils.isEmpty(password) -> {
                context.toast("password can not be empty! ")
                return
            }
            TextUtils.isEmpty(rePassword) -> {
                context.toast("rePassword can not be empty! ")
                return
            }
            else -> model.getRegister(userName, password, rePassword, this)
        }
    }

    override fun onGetLogin(bean: LoginBean?) {
        bean?.let {
            view.showLoginResult(it)
        }
    }

    override fun onGetRegister(bean: LoginBean?) {
        bean?.let { view.showRegisterResult(bean) }
    }
}