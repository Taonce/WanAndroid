package com.taonce.wankotlin.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.contract.ISearchView
import com.taonce.wankotlin.presenter.SearchPresenter
import com.taonce.wankotlin.ui.adapter.HomePageAdapter
import com.taonce.wankotlin.ui.adapter.SearchResultAdapter
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.item_common_head.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/12
 * Project: WanKotlin
 * Desc:
 */

class SearchResultActivity : BaseMVPActivity<ISearchView, SearchPresenter>(),
    ISearchView {

    private val mPresenter: SearchPresenter by lazy { getPresenter() }
    private var mAdapter: SearchResultAdapter? = null
    private lateinit var mKey: String
    private var index: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_search_result

    override fun initData() {
        mPresenter.getSearch(index, mKey)
    }

    override fun initView() {
        mKey = intent.getStringExtra(Constant.SEARCH_KEY)
        iv_head_back.setOnClickListener { finish() }
        tv_head_title.text = mKey
    }

    override fun initEvent() {
    }

    override fun getPresenter(): SearchPresenter = SearchPresenter(this)

    override fun showSearchData(queryBean: QueryBean) {
        queryBean.data.datas?.let { itemData ->
            mAdapter = SearchResultAdapter(
                this,
                R.layout.item_home_article,
                itemData
            )
            rv_search_result.adapter = mAdapter
            rv_search_result.layoutManager = LinearLayoutManager(this@SearchResultActivity)
            mAdapter?.notifyDataSetChanged()
            mAdapter?.setOnItemClickListener { position ->
                toCommonX5Activity(this@SearchResultActivity, itemData[position].link)
            }
        }
    }
}

