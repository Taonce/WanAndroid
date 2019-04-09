package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.HotKeyBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

interface IHotKeyView {
    fun showHotKey(hotKeyBean: HotKeyBean)
}

interface IHotKeyModel {
    fun getHotKey(listener: OnGetHotKeyListener)

    interface OnGetHotKeyListener {
        fun onListener(hotKeyBean: HotKeyBean?)
    }
}
