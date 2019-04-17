package com.taonce.wankotlin.contract

import com.taonce.wankotlin.base.IBaseView
import com.taonce.wankotlin.bean.LoginBean


interface ILogoutView : IBaseView {
    fun showLogout(bean: LoginBean)
}

interface ILogoutModel {
    fun getLogout(listener: OnGetLogoutListener)

    interface OnGetLogoutListener {
        fun onListener(bean: LoginBean?)
    }
}
