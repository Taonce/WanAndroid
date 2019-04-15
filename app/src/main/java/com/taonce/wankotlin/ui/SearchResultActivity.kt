package com.taonce.wankotlin.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.contract.ISearchView
import com.taonce.wankotlin.presenter.SearchPresenter
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
    private val mQueryResultData: MutableList<QueryBean.Data.DatasItem> = mutableListOf()
    private var updateIndex: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_search_result

    override fun initData() {
        mPresenter.getSearch(index, mKey)
    }

    override fun initView() {
        mKey = intent.getStringExtra(Constant.SEARCH_KEY)
        iv_head_back.setOnClickListener { finish() }
        tv_head_title.text = mKey
        mAdapter = SearchResultAdapter(
            this,
            R.layout.item_home_article,
            mQueryResultData
        )
        rv_search_result.adapter = mAdapter
        rv_search_result.layoutManager = LinearLayoutManager(this@SearchResultActivity)
        srl_search_result.setRefreshHeader(ClassicsHeader(this@SearchResultActivity))
        srl_search_result.setRefreshFooter(ClassicsFooter(this@SearchResultActivity))
    }

    override fun initEvent() {
        mAdapter?.setOnItemClickListener { position ->
            toCommonX5Activity(this@SearchResultActivity, mQueryResultData[position].link)
        }
        srl_search_result.setOnRefreshListener { mPresenter.getSearch(index, mKey) }
        srl_search_result.setOnLoadMoreListener { mPresenter.getSearch(index, mKey) }
    }

    override fun getPresenter(): SearchPresenter = SearchPresenter(this)

    override fun showSearchData(queryBean: MutableList<QueryBean.Data.DatasItem>) {
        index++
        updateIndex = if (mQueryResultData.isEmpty()) 0 else mQueryResultData.size - 1
        mQueryResultData.addAll(queryBean)
        mAdapter?.addListData(queryBean, refreshIndex = updateIndex)

    }

    override fun refreshFinished() {
        srl_search_result.finishRefresh()
        srl_search_result.finishLoadMore()
    }
}

