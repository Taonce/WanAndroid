package com.taonce.wankotlin.net

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import com.taonce.utilmodule.getSP
import com.taonce.utilmodule.showInfo
import com.taonce.wankotlin.App
import com.taonce.wankotlin.base.Constant
import okhttp3.*
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc:
 */

class OkHttpUtil private constructor() {
    private var okHttpClient: OkHttpClient? = null
    // 自定义TAG的Log
    private val okHttpLog: OkHttpLog by lazy { OkHttpLog() }
    // 连接超时时间
    private val connectionTime: Long = 10L
    // 写入超时时间
    private val writeTime: Long = 10L
    // 读取超时时间
    private val readTime: Long = 30L
    // 缓存文件
    private val cacheFile: File = File(App.mInstance.cacheDir.absolutePath)
    // 缓存文件大小
    private val maxSize: Long = 8 * 1024 * 1024
    // OkHttpCache
    private var cache: Cache

    companion object {
        val mInstance: OkHttpUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpUtil()
        }
    }

    init {
        // 初始化缓存文件
        if (!cacheFile.exists()) cacheFile.mkdir()
        cache = Cache(cacheFile, maxSize)
    }

    // 配置OkHttp
    fun getHttpClient(): OkHttpClient {
        val hashMap: HashMap<String, MutableList<Cookie>> = HashMap()
        if (null == okHttpClient) {
            okHttpClient = OkHttpClient.Builder()
                .cookieJar(object : CookieJar {
                    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                        hashMap[url.host()] = cookies
                    }

                    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
                        return hashMap[url.host()] ?: mutableListOf()
                    }
                })
                .connectTimeout(connectionTime, TimeUnit.SECONDS)
                .readTimeout(readTime, TimeUnit.SECONDS)
                .writeTimeout(writeTime, TimeUnit.SECONDS)
                .addInterceptor(LogInterceptor(okHttpLog))
                .addInterceptor(HeadIntercept())
                .addInterceptor(CookieIntercept())
                .cache(cache)
                .build()
        }
        return okHttpClient!!
    }

    // 自定义OkHttpLog
    class OkHttpLog : LogInterceptor.Logger {
        override fun log(message: String?) {
            showInfo(tag = "okhttp", msg = " $message")
        }
    }

    // add Cookie
    class HeadIntercept : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val builder: Request.Builder = request.newBuilder()
            builder.addHeader("Content-type", "application/json; charset=utf-8")
            val domain: String = request.url().host()
            if (domain.isNotEmpty()) {
                val cookies: String = App.mInstance.getSP(Constant.SP_COOKIE, "") as String
                builder.addHeader(Constant.COOKIE, cookies)
            }
            return chain.proceed(builder.build())
        }
    }

    /**
     * save Cookie
     */
    class CookieIntercept : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val response: Response = chain.proceed(request)
            val requestUrl: String = request.url().toString()
            if ((requestUrl.contains(Constant.wan_login)
                        || requestUrl.contains(Constant.wan_register))
                && response.headers(Constant.SET_COOKIE).isNotEmpty()
            ) {
                val cookies: MutableList<String> = response.headers(Constant.SET_COOKIE)
                val cookie: String = CookieUtil.encodeCookie(cookies)
                CookieUtil.saveCookie(cookie)
            }
            return response
        }
    }
}