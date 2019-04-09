package com.taonce.wankotlin.presenter

import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.TreeBean
import com.taonce.wankotlin.contract.ITreeModel
import com.taonce.wankotlin.contract.ITreeView
import com.taonce.wankotlin.model.TreeModel


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class TreePresenter(private val view: ITreeView) : BasePresenter<ITreeView>(), ITreeModel.OnGetTreeListener {
    private val model: ITreeModel = TreeModel()

    fun getTree(): Unit = model.getTreeSystem(this)


    override fun onListener(treeBean: TreeBean?) {
        treeBean?.let { view.showTreeSystem(it) }
    }
}