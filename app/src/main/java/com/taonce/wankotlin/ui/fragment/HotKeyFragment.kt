package com.taonce.wankotlin.ui.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseFragment
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.contract.IHotKeyView
import com.taonce.wankotlin.presenter.HotKeyPresenter
import com.taonce.wankotlin.ui.ChapterArticleActivity
import com.taonce.wankotlin.ui.adapter.HotKeyAdapter
import kotlinx.android.synthetic.main.fragment_hot_key.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class HotKeyFragment : BaseFragment(), IHotKeyView {

    private lateinit var mPresenter: HotKeyPresenter
    private var mAdapter: HotKeyAdapter? = null
    private val mData: MutableList<HotKeyBean.DataItem> = mutableListOf()

    override fun getLayoutId(): Int = R.layout.fragment_hot_key

    override fun initData() {
        mPresenter = HotKeyPresenter(this)
        mPresenter.getHotKey()
    }

    override fun initView() {

    }

    override fun initEvent() {
    }

    override fun showHotKey(hotKeyBean: HotKeyBean) {
        hotKeyBean.data?.let {
            mData.addAll(it)
            mAdapter = HotKeyAdapter(this.context!!, R.layout.item_hot_key, mData)
            rv_hot_key.adapter = mAdapter
            rv_hot_key.layoutManager = LinearLayoutManager(
                this.context!!
            )
            mAdapter?.notifyDataSetChanged()
        }
    }
}
