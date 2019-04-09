package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.TreeBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

interface ITreeView {
    fun showTreeSystem(treeBean: TreeBean)
}

interface ITreeModel {
    fun getTreeSystem(listener: OnGetTreeListener)

    interface OnGetTreeListener {
        fun onListener(treeBean: TreeBean?)
    }
}

