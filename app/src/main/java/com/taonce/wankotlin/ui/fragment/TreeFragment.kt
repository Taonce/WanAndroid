package com.taonce.wankotlin.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseFragment
import com.taonce.wankotlin.bean.TreeBean
import com.taonce.wankotlin.contract.ITreeView
import com.taonce.wankotlin.presenter.TreePresenter
import com.taonce.wankotlin.ui.adapter.TreeAdapter
import kotlinx.android.synthetic.main.fragment_tree.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class TreeFragment : BaseFragment(), ITreeView {

    private lateinit var treePresenter: TreePresenter
    private lateinit var treeAdapter: TreeAdapter
    private var mData: MutableList<TreeBean.DataItem> = mutableListOf()

    override fun getLayoutId(): Int = R.layout.fragment_tree

    override fun initData() {
        treePresenter = TreePresenter(this)

    }

    override fun initView() {
    }

    override fun initEvent() {
    }

    override fun onResume() {
        super.onResume()
        if (mData.isEmpty()) {
            treePresenter.getTree()
        }
    }

    override fun showTreeSystem(treeBean: TreeBean) {
        treeBean.data?.let {
            mData.addAll(it)
            treeAdapter = TreeAdapter(this.context!!, R.layout.item_tree, mData)
            rv_tree.layoutManager = GridLayoutManager(this.context, 2)
            rv_tree.adapter = treeAdapter
            treeAdapter.notifyDataSetChanged()
        } ?: toast("")
    }
}
