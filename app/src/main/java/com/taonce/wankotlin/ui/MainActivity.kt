package com.taonce.wankotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taonce.utilmodule.showDebug
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.QueryBean
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        RetrofitUtil.mInstance.getService().logout()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BaseBean> {
                override fun onSuccess(value: BaseBean) {
                }

                override fun onFailed() {
                }
            })
    }
}
