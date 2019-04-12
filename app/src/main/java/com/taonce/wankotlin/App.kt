package com.taonce.wankotlin

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.base.Constant
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.smtt.sdk.QbSdk
import org.litepal.LitePal
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
        // litePal init
        LitePal.initialize(this@App)
        // bugly init
        CrashReport.initCrashReport(this@App, Constant.BUGLY_ID, false)
        // X5 web init
        initX5()
    }

    private fun initX5() {
        val callBack: QbSdk.PreInitCallback = object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
            }

            override fun onViewInitFinished(p0: Boolean) {
            }
        }
        QbSdk.initX5Environment(this@App, callBack)
    }
}


