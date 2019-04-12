package com.taonce.wankotlin.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.taonce.utilmodule.start
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseFragment
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.HotKeyDB
import com.taonce.wankotlin.contract.IHotKeyView
import com.taonce.wankotlin.presenter.HotKeyPresenter
import com.taonce.wankotlin.ui.SearchResultActivity
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
            mAdapter?.setOnItemClickListener { position ->
                val key: String = mData[position].name
                mPresenter.saveKey2DB(key)
                intentSearchResultActivity(key)
            }
            rv_hot_key.adapter = mAdapter
            rv_hot_key.layoutManager = LinearLayoutManager(
                this.context!!
            )
            mAdapter?.notifyDataSetChanged()
        }
    }

    private fun intentSearchResultActivity(key: String) {
        start(SearchResultActivity::class.java, mapOf(Pair(Constant.SEARCH_KEY, key)))
    }

    override fun showDB(hotKeyDBList: MutableList<HotKeyDB>) {
    }
}
