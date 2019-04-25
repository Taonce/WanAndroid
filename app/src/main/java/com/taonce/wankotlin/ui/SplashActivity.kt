package com.taonce.wankotlin.ui

import com.taonce.utilmodule.start
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {
        GlobalScope.launch {
            delay(1000L)
            jump2Main()
        }
        tv_splash_jump.setOnClickListener {
            jump2Main()
        }
    }

    private fun jump2Main(): Unit = start(MainActivity::class.java, isFinished = true)

}