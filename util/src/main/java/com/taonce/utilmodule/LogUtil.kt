package com.taonce.utilmodule


import android.util.Log


/**
 * Author: taoyongxiang
 * Date: 2019/3/28
 * Desc: 常用的日志打印类
 */
const val isShow: Boolean = true

fun showDebug(
    tag: String = "taonce",
    msg: String
) {
    if (isShow) {
        Log.d(tag, msg)
    }
}

fun showError(
    tag: String = "taonce",
    msg: String
) {
    if (isShow)
        Log.e(tag, msg)
}

fun showInfo(
    tag: String = "taonce",
    msg: String
) {
    if (isShow)
        Log.i(tag, msg)
}

fun showWarning(
    tag: String = "taonce",
    msg: String
) {
    if (isShow)
        Log.w(tag, msg)
}