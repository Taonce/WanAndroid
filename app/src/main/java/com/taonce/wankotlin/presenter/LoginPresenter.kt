package com.taonce.wankotlin.presenter

import com.taonce.wankotlin.base.BaseBean
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

    fun login(userName: String, password: String) {
        model.getLogin(userName, password, this)
    }

    fun register(userName: String, password: String, rePassword: String) {
        model.getRegister(userName, password, rePassword, this)
    }

    override fun onGetLogin(bean: LoginBean?) {
        bean?.let {
            view.showLoginResult(it)
        }
        bean ?: let {

        }
    }

    override fun onGetRegister(bean: BaseBean?) {
        bean?.let { view.showRegisterResult(bean) }
    }
}