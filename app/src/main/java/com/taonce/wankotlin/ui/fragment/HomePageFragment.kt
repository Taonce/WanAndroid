package com.taonce.wankotlin.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.taonce.utilmodule.formatDate2Day
import com.taonce.utilmodule.isShow
import com.taonce.utilmodule.showDebug
import com.taonce.utilmodule.showInfo
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseFragment
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.ArticleBean
import com.taonce.wankotlin.bean.BannerBean
import com.taonce.wankotlin.bean.HomePageBean
import com.taonce.wankotlin.contract.IHomePageView
import com.taonce.wankotlin.presenter.HomePagePresenter
import com.taonce.wankotlin.ui.CommonX5Activity
import com.taonce.wankotlin.ui.adapter.HomePageAdapter
import com.taonce.wankotlin.ui.toCommonX5Activity
import com.taonce.wankotlin.util.loadImage
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : BaseFragment(), IHomePageView {
    // banner url
    private var mBannerImageUrl: MutableList<String> = mutableListOf()
    private var mBannerUrl: MutableList<String> = mutableListOf()

    // article data
    private var mArticleData: MutableList<HomePageBean.Data.DatasItem> = mutableListOf()
    private var mAdapter: HomePageAdapter? = null
    private lateinit var mPresenter: HomePagePresenter

    // add rv head layout
    private var mRootView: ViewGroup? = null
    private var mHeadView: View? = null
    private var banner: Banner? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = container
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_page

    override fun initView() {
        // setting banner
        mHeadView = LayoutInflater.from(this.context!!).inflate(R.layout.head_home_page, mRootView, false)
        banner = mHeadView?.findViewById(R.id.banner_home)
        banner?.apply {
            showDebug(msg = "setting banner")
            setBannerAnimation(Transformer.DepthPage)
            isAutoPlay(true)
            setDelayTime(5000)
            setIndicatorGravity(BannerConfig.CENTER)
        }
    }

    override fun initData() {
        mPresenter = HomePagePresenter(this)
        mPresenter.getHomePageData(0)
        mPresenter.getBanner()
        showLoading()
    }

    override fun initEvent() {
        banner?.setOnBannerListener {
            val urlFrom: String = mBannerUrl[it]
            if (urlFrom.isNotEmpty()) {
                toCommonX5Activity(this.context!!, urlFrom, isShow = false)
            } else {
                showInfo(msg = "banner url is empty! ")
            }
        }
    }

    override fun hideLoadingView() {
        hideLoading()
    }

    override fun showHomePageData(homePageBean: HomePageBean) {
        homePageBean.data.datas?.let {
            mArticleData.addAll(0, it)
            mAdapter = HomePageAdapter(this.context!!, R.layout.item_home_article, mArticleData, mHeadView!!)
            mAdapter?.setOnItemClickListener { index ->
                showDebug(msg = "item click enter, position is $index")
                val item: HomePageBean.Data.DatasItem = mArticleData[index]
                if (item.link.isNotEmpty()) {
                    toCommonX5Activity(
                        this.context!!, url = item.link,
                        isCollected = item.collect,
                        articleId = item.id,
                        collectTime = item.niceDate,
                        publishTime = item.publishTime.formatDate2Day(),
                        author = item.author,
                        title = item.title
                    )
                } else {
                    showInfo(msg = "article url is empty! ")
                }
            }
            rv_home.adapter = mAdapter
            rv_home.layoutManager = LinearLayoutManager(this.context!!)
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun showBanner(bannerBean: BannerBean) {
        bannerBean.data.let {
            it.forEach { url ->
                mBannerImageUrl.add(url.imagePath)
                mBannerUrl.add(url.url)
            }
        }
        banner?.apply {
            showDebug(msg = "show banner and urls is $mBannerImageUrl")
            setImages(mBannerImageUrl)
            setImageLoader(GlideImageLoader())
            start()
        }
    }

    inner class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            this@HomePageFragment.context?.loadImage(path as String, imageView)
        }
    }
}