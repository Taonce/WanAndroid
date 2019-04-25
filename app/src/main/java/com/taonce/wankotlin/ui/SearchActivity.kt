package com.taonce.wankotlin.ui

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.taonce.utilmodule.start
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.db.HotKeyDB
import com.taonce.wankotlin.contract.IHotKeyView
import com.taonce.wankotlin.presenter.HotKeyPresenter
import com.taonce.wankotlin.ui.adapter.HotKeyDBAdapter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity :
    BaseMVPActivity<IHotKeyView, HotKeyPresenter>(),
    IHotKeyView {

    private var mHotKeys: MutableList<String> = mutableListOf()
    private val mPresenter: HotKeyPresenter by lazy { getPresenter() }
    private var mDBAdapter: HotKeyDBAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initData() {
        mPresenter.getHotKey()
        mPresenter.findKeyAll()
    }

    override fun initView() {

    }

    override fun initEvent() {
        iv_search_back.setOnClickListener { finish() }
        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == 0) {
                val content: String = et_search.text.toString().trim()
                mPresenter.saveKey2DB(key = content)
                intentSearchResultActivity(content)
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

    override fun showDB(hotKeyDBList: MutableList<HotKeyDB>) {
        mDBAdapter = HotKeyDBAdapter(
            this@SearchActivity,
            R.layout.item_hot_key_db,
            hotKeyDBList
        )
        rv_key_db.adapter = mDBAdapter
        rv_key_db.layoutManager = LinearLayoutManager(this@SearchActivity)
        mDBAdapter?.notifyDataSetChanged()
        mDBAdapter?.setOnItemClickListener { position ->
            val key: String = hotKeyDBList[position].key
            mPresenter.saveKey2DB(key)
            intentSearchResultActivity(key)
        }
    }

    private fun settingFlowLayout() {
        fl_search.adapter = object : TagAdapter<String>(mHotKeys) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tv = TextView(this@SearchActivity)
                tv.apply {
                    text = t
                    textSize = 14f
                    setBackgroundResource(R.drawable.flow_layout_bg)
                }
                return tv
            }
        }
        fl_search.setOnTagClickListener { _, position, _ ->
            val key: String = mHotKeys[position]
            mPresenter.saveKey2DB(key)
            intentSearchResultActivity(key)
            return@setOnTagClickListener true
        }
    }

    private fun intentSearchResultActivity(key: String) {
        start(SearchResultActivity::class.java, mapOf(Pair(Constant.SEARCH_KEY, key)))
    }
}