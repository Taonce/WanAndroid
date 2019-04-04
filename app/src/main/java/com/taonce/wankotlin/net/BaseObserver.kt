package com.taonce.wankotlin.net

import android.net.ParseException
import com.google.gson.JsonParseException
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.base.BaseBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc:
 */

interface BaseObserver<T : BaseBean> : Observer<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(value: T) {
        // 这里面还可以加入额外的一些code判断，因为我这没返回code，所以就不加了
        // gank接口返回的是error，所以这里用error来演示
        value.let {
            if (1 == it.errorCode) {
                onSuccess(it)
            } else {
                App.mInstance.toast("接口返回错误")
                onFailed()
            }
        }
        value ?: App.mInstance.toast("无数据")
    }

    override fun onError(e: Throwable) {
        onErrorAble(e)
    }

    // 成功的处理就交给model层了
    fun onSuccess(value: T)

    // 失败的回调之所以交给model层，是因为需要通过model层的listener回调给presenter层，hideProgress
    fun onFailed()

    // 捕捉异常信息，并吐司提示
    fun onErrorAble(e: Throwable?) {
        when (e) {
            is NullPointerException -> App.mInstance.toast("接口挂了")
            is HttpException -> App.mInstance.toast("Http错误")
            is ConnectException -> App.mInstance.toast("连接错误")
            is UnknownHostException -> App.mInstance.toast("找不到主机")
            is InterruptedException -> App.mInstance.toast("连接超时")
            is SocketTimeoutException -> App.mInstance.toast("请求超时")
            is JsonParseException, is JSONException, is ParseException -> App.mInstance.toast("解析错误")
            else -> App.mInstance.toast("未知错误")
        }
        onFailed()
    }
}