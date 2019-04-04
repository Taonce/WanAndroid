package com.taonce.wankotlin.base


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc:
 */

object Constant {
    const val base_url = "https://www.wanandroid.com/"

    /**
     * 登录注册
     */
    const val wan_login = "user/login" // login
    const val wan_register = "user/register" // register
    const val wan_logout = "user/logout/json" // logout

    /**
     * 公众号
     */
    const val wan_wxarticle_chapters = "wxarticle/chapters/json" // wxarticle_chapters
    const val wan_wxarticle_list = "wxarticle/list/json" // wxarticle_list
    const val wan_wxarticle_history = "wxarticle/list/" // list/405/1/json?k=Java

    /**
     * 文章体系
     */
    const val wan_tree = "tree/json" // tree
    const val wan_tree_article = "article/list/" // index/json?cid=60

    /**
     * 收藏
     */
    const val wan_collect = "lg/collect/list/" // index/json: 'index' in url
    const val wan_collect_inner = "lg/collect/1165/json" // post "article_id" in url
    const val wan_collect_out = "lg/collect/add/json" // post title,author,link in body
    const val wan_unCollect_article = "lg/uncollect_originId/2333/json" // post "article_id" in url
    const val wan_unCollect = "lg/uncollect/2805/json" // post "article_id" in url, originId in body

    /**
     * 搜索
     */
    const val wan_hot_key = "hotkey/json" // hot_key
    const val wan_query = "article/query/" // index/post: 'index' in url, 'k' in body
}