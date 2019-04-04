package com.taonce.wankotlin.bean

import com.taonce.wankotlin.base.BaseBean

data class LoginBean(
    val data: LoginData
) : BaseBean() {
    data class LoginData(
        val password: String = "",
        val icon: String = "",
        val id: Int = 0,
        val type: Int = 0,
        val email: String = "",
        val token: String = "",
        val username: String = ""
    )
}