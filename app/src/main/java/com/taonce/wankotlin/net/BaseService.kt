package com.taonce.wankotlin.net

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.*
import io.reactivex.CompletableOnSubscribe
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
    fun logout(): Observable<LoginBean>

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

    /**
     * 首页推荐文章
     */
    @GET(value = Constant.wan_home_page + "{index}/json")
    fun getHomePageArticle(
        @Path("index") index: Int = 0
    ): Observable<HomePageBean>

    /**
     * Banner
     */
    @GET(value = Constant.wan_banner)
    fun getBanner(): Observable<BannerBean>

    /**
     * 收藏列表
     */
    @GET(value = Constant.wan_collect + "{index}/json")
    fun getCollectionList(
        @Path(value = "index") index: Int = 0
    ): Observable<CollectionListBean>

    /**
     * 取消收藏，收藏列表界面的取消收藏
     */
    @POST(value = Constant.wan_unCollect + "{id}/json")
    @FormUrlEncoded
    fun cancelCollection(
        @Path(value = "id") id: Int = -1,
        @Field(value = "originId") originId: Int = -1
    ): Observable<BaseBean>

    /**
     * 取消收藏，文章详情界面
     */
    @POST(value = Constant.wan_unCollect_article + "{id}/json")
    fun unCollection(
        @Path(value = "id") id: Int = -1
    ): Observable<BaseBean>

    /**
     * 收藏站内文章
     */
    @POST(value = Constant.wan_collect_inner+"{id}/json")
    fun collectionInner(
        @Path(value = "id") id: Int = -1
    ): Observable<BaseBean>
}
