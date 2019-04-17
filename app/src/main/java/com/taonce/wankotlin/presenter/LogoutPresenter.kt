package com.taonce.wankotlin.presenter

import com.taonce.utilmodule.getSP
import com.taonce.utilmodule.putSP
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.contract.ILogoutModel
import com.taonce.wankotlin.contract.ILogoutView
import com.taonce.wankotlin.model.LogoutModel


class LogoutPresenter(private val mView: ILogoutView) : BasePresenter<ILogoutView>()
    , ILogoutModel.OnGetLogoutListener {

    private val model: ILogoutModel = LogoutModel()

    fun logout() {
        if ((App.mInstance.getSP("userName", "") as String).isEmpty()) {
            App.mInstance.toast(R.string.pleaseLogin)
        } else {
            mView.showLoading()
            model.getLogout(this)
        }
    }

    override fun onListener(bean: LoginBean?) {
        mView.hideLoading()
        bean?.let {
            App.mInstance.putSP("userName", "")
            App.mInstance.toast(R.string.logoutSuccess)
            mView.showLogout(it)
        }
    }
}

