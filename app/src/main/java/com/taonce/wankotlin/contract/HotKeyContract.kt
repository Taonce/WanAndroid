package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.db.HotKeyDB


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

interface IHotKeyView {
    fun showHotKey(hotKeyBean: HotKeyBean)

    fun showDB(hotKeyDBList: MutableList<HotKeyDB>)
}

interface IHotKeyModel {
    fun getHotKey(listener: OnGetHotKeyListener)

    fun saveKey2DB(key: String)

    fun getKeyAll(listener:OnGetHotKeyListener)

    interface OnGetHotKeyListener {
        fun onListener(hotKeyBean: HotKeyBean?)

        fun onDBListener(hotKeyDBList: MutableList<HotKeyDB>)
    }
}
