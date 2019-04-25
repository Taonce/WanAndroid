package com.taonce.wankotlin.presenter

import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.db.HotKeyDB
import com.taonce.wankotlin.contract.IHotKeyModel
import com.taonce.wankotlin.contract.IHotKeyView
import com.taonce.wankotlin.model.HotKeyModel


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class HotKeyPresenter(private val mView: IHotKeyView) :
    BasePresenter<IHotKeyView>(),
    IHotKeyModel.OnGetHotKeyListener {

    private val model: IHotKeyModel = HotKeyModel()

    fun getHotKey(): Unit = model.getHotKey(this)

    fun saveKey2DB(key: String): Unit = model.saveKey2DB(key)

    fun findKeyAll(): Unit = model.getKeyAll(this)

    override fun onListener(hotKeyBean: HotKeyBean?) {
        hotKeyBean?.let { mView.showHotKey(it) }
    }

    override fun onDBListener(hotKeyDBList: MutableList<HotKeyDB>) {
        if (hotKeyDBList.isNotEmpty()) mView.showDB(hotKeyDBList)
    }
}