package com.taonce.wankotlin.net

import android.content.Context
import android.net.ParseException
import com.google.gson.JsonParseException
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.App
import com.taonce.wankotlin.R
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
        value.let {
            if (0 == it.errorCode) {
                onSuccess(it)
            } else {
                App.mInstance.toast(it.errorMsg)
                onFailed()
            }
        }
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
        val context: Context = App.mInstance
        when (e) {
            is NullPointerException -> App.mInstance.toast(context.getString(R.string.interface_error))
            is HttpException -> App.mInstance.toast(context.getString(R.string.http_error))
            is ConnectException -> App.mInstance.toast(context.getString(R.string.connect_error))
            is UnknownHostException -> App.mInstance.toast(context.getString(R.string.host_error))
            is InterruptedException -> App.mInstance.toast(context.getString(R.string.connect_timeout))
            is SocketTimeoutException -> App.mInstance.toast(context.getString(R.string.request_timeout))
            is JsonParseException, is JSONException, is ParseException -> App.mInstance.toast(context.getString(R.string.parse_error))
            else -> App.mInstance.toast(context.getString(R.string.interface_error))
        }
        onFailed()
    }
}