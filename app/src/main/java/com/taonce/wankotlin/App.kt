package com.taonce.wankotlin

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.base.Constant
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.smtt.sdk.QbSdk
import kotlin.math.min


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc:
 */

class App : Application() {
    companion object {
        @JvmStatic
        lateinit var mInstance: App
            private set
    }

    override fun onCreate() {
        mInstance = this
        super.onCreate()
        CrashReport.initCrashReport(this@App, Constant.BUGLY_ID, false)
        initX5()
    }

    private fun initX5() {
        val callBack: QbSdk.PreInitCallback = object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
            }

            override fun onViewInitFinished(p0: Boolean) {
            }
        }
        QbSdk.initX5Environment(mInstance, callBack)
    }
}


