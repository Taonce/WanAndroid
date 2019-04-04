package com.taonce.wankotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taonce.wankotlin.R
import com.taonce.wankotlin.ui.inter.IBaseView


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc: 不涉及 V和P 层
 */

abstract class BaseActivity : AppCompatActivity(), IBaseView {
    private var baseLoadingView: BaseLoadingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initLoadingView()
        initData()
        initView()
        initEvent()
    }

    abstract fun getLayoutId(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun initEvent()


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

        // 销毁dialog
        if (null != baseLoadingView && baseLoadingView!!.isShowing) {
            baseLoadingView!!.dismiss()
        }
        baseLoadingView = null

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun initLoadingView() {
        baseLoadingView = BaseLoadingView(this, R.style.transparent_dialog)
    }

    private fun showLoadingView() {
        if (null != baseLoadingView) {
            baseLoadingView!!.show()
        } else {
            initLoadingView()
            baseLoadingView!!.show()
        }
    }

    private fun hideLoadingView() {
        if (null != baseLoadingView && baseLoadingView!!.isShowing) {
            baseLoadingView!!.cancel()
        }
    }

    /**
     * 将显示dialog和取消dialog放在了基础类中
     */
    override fun showLoading() {
        showLoadingView()
    }

    override fun hideLoading() {
        hideLoadingView()
    }
}
