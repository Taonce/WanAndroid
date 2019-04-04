package com.taonce.wankotlin

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.taonce.utilmodule.toast
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
    }

}


