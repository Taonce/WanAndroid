package com.taonce.wankotlin.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseActivity
import com.taonce.wankotlin.ui.fragment.HotKeyFragment
import com.taonce.wankotlin.ui.fragment.TreeFragment
import com.taonce.wankotlin.ui.fragment.WxChapterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var fragments: List<Fragment>

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {
        fragments = listOf(HotKeyFragment(), TreeFragment(), WxChapterFragment())
    }

    override fun initView() {
        vp_main.adapter = MyAdapter(supportFragmentManager)
        vp_main.pageMargin = 16
        vp_main.offscreenPageLimit = 2
    }

    override fun initEvent() {
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