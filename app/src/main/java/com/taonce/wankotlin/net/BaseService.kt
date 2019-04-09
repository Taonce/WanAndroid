package com.taonce.wankotlin.net

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.*
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
    ): Observable<LoginBean>

    /**
     * 退出登录
     */
    @GET(value = Constant.wan_logout)
    fun logout(): Observable<BaseBean>

    /**
     * 获取公众号列表
     */
    @GET(value = Constant.wan_wxarticle_chapters)
    fun wxChapters(): Observable<ChapterBean>

    /**
     * 获取某个公众号的历史文章
     */
    @GET(value = Constant.wan_wxarticle_list + "{id}/{index}/json")
    fun wxHistory(
        @Path(value = "id") id: String,
        @Path(value = "index") index: Int = 0
    ): Observable<WxHistoryBean>

    /**
     * 获取知识体系
     */
    @GET(value = Constant.wan_tree)
    fun getTree(): Observable<TreeBean>

    /**
     * 获取知识体系下的文章
     */
    @GET(value = Constant.wan_tree_article + "{index}/json?cid={cid}")
    fun getArticle(
        @Path(value = "index") index: Int = 0,
        @Path(value = "cid") cid: String
    ): Observable<ArticleBean>

    @GET(value = Constant.wan_home_page + "{index}/json")
    fun getHomePageArticle(
        @Path("index") index: Int = 0
    ):BaseObserver<HomePageBean>
}
