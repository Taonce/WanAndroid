package com.taonce.wankotlin.ui

import com.taonce.utilmodule.start
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseActivity
import kotlin.concurrent.thread

class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {
        Thread.sleep(1000L)
        thread {
            start(MainActivity::class.java, isFinished = true)
        }
    }

}