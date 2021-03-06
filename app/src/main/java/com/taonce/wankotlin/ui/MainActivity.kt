package com.taonce.wankotlin.ui

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.taonce.utilmodule.showDebug
import com.taonce.utilmodule.showSnackBar
import com.taonce.utilmodule.start
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseActivity
import com.taonce.wankotlin.ui.fragment.HomePageFragment
import com.taonce.wankotlin.ui.fragment.HotKeyFragment
import com.taonce.wankotlin.ui.fragment.TreeFragment
import com.taonce.wankotlin.ui.fragment.WxChapterFragment
import com.taonce.wankotlin.util.DepthPageTransformer
import com.taonce.wankotlin.util.RotaScalePageTransformer
import com.taonce.wankotlin.util.ZoomOutPageTransformer
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var fragments: List<Fragment>

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {
        fragments = listOf(HomePageFragment(), HotKeyFragment(), TreeFragment(), WxChapterFragment())
    }

    override fun initView() {
        vp_main.adapter = MyAdapter(supportFragmentManager)
        vp_main.pageMargin = 16
        vp_main.currentItem = 0
        vp_main.offscreenPageLimit = 3
//        vp_main.setPageTransformer(true, RotaScalePageTransformer())
    }

    override fun initEvent() {
        et_search.setOnClickListener { start(SearchActivity::class.java) }
        fa_main.setOnClickListener { start(SettingActivity::class.java) }
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int = fragments.size

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            return super.instantiateItem(container, position)
        }
    }
}
