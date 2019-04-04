package com.taonce.wankotlin.net

import com.taonce.wankotlin.base.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc: 网络请求的服务接口
 */

interface BaseService {

    /**
     * 根据category获取Android、ios等数据
     * category：类型
     * count：分页的一页数据
     * page：第几页
     */
    @GET("search/query/listview/category/{category}/count/{count}/page/{page}")
    fun getCategoryData(@Path("category") category: String,
                        @Path("count") count: Int,
                        @Path("page") page: Int): Observable<BaseBean>

}
