package com.taonce.wankotlin.net

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.bean.QueryBean
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc: 网络请求的服务接口
 */

interface BaseService {

    /**
     * 搜索热词
     */
    @GET(value = Constant.wan_hot_key)
    fun getHotKey(): Observable<HotKeyBean>

    /**
     * 搜索指定内容
     */
    @POST(value = Constant.wan_query + "{index}/json")
    @FormUrlEncoded
    fun query(
        @Path(value = "index") index: Int = 0,
        @Field(value = "k") key: String
    ): Observable<QueryBean>

    /**
     * 登录
     */
    @POST(value = Constant.wan_login)
    @FormUrlEncoded
    fun login(
        @Field(value = "username") userName: String,
        @Field(value = "password") passWord: String
    ): Observable<LoginBean>

    /**
     * 注册
     */
    @POST(value = Constant.wan_register)
    @FormUrlEncoded
    fun register(
        @Field(value = "username") userName: String,
        @Field(value = "password") passWord: String,
        @Field(value = "repassword") rePassWord: String
    ): Observable<BaseBean>

    /**
     * 退出登录
     */
    @GET(value = Constant.wan_logout)
    fun logout(): Observable<BaseBean>
}
