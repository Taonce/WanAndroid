package com.taonce.wankotlin.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.view.marginStart
import com.taonce.utilmodule.showInfo
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.contract.IHotKeyView
import com.taonce.wankotlin.contract.ISearchView
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers
import com.taonce.wankotlin.presenter.HotKeyPresenter
import com.taonce.wankotlin.presenter.SearchPresenter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity :
    BaseMVPActivity<IHotKeyView, HotKeyPresenter>(),
    IHotKeyView,
    ISearchView {

    private var mHotKeys: MutableList<String> = mutableListOf()
    private val mPresenter: HotKeyPresenter by lazy { getPresenter() }
    private lateinit var mSearchPresenter: SearchPresenter

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initData() {
        mSearchPresenter = SearchPresenter(this)
        mPresenter.getHotKey()
    }

    override fun initView() {

    }

    override fun initEvent() {
        iv_search_back.setOnClickListener { finish() }
        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == 0) {
                val content: String = et_search.text.toString().trim()
                if (content.isNotEmpty()) {
                    mSearchPresenter.getSearch(0, content)
                }
                //隐藏软键盘
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
            }
            return@setOnEditorActionListener false
        }
    }

    override fun getPresenter(): HotKeyPresenter = HotKeyPresenter(this)

    override fun showHotKey(hotKeyBean: HotKeyBean) {
        hotKeyBean.data?.forEach {
            mHotKeys.add(it.name)
        }
        settingFlowLayout()
    }

    override fun showSearchData(queryBean: QueryBean) {
    }

    private fun settingFlowLayout() {
        fl_search.adapter = object : TagAdapter<String>(mHotKeys) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tv = TextView(this@SearchActivity)
                tv.apply {
                    text = t
                    textSize = 16f
                    setPadding(5, 5, 5, 5)
                }
                return tv
            }
        }
        fl_search.setOnTagClickListener { _, position, _ ->
            if (mHotKeys[position].isNotEmpty()) {
                mSearchPresenter.getSearch(0, mHotKeys[position])
            }
            return@setOnTagClickListener true
        }
    }
}