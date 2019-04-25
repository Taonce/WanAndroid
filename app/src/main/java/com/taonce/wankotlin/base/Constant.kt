package com.taonce.wankotlin.base

import okhttp3.Cookie


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc:
 */

object Constant {
    const val BASE_URL = "https://www.wanandroid.com/"
    const val BUGLY_ID = "6ecde0d53a"
    const val SET_COOKIE = "set-cookie"
    const val COOKIE = "Cookie"

    // sp tag
    const val SP_COOKIE = "cookie"
    const val SP_USERNAME = "userName"

    // intent tag
    const val X5_URL = "url"
    const val X5_IS_COLLECTED = "isCollected"
    const val X5_ARTICLE_ID = "articleId"
    const val X5_IS_SHOW = "isShow"
    const val X5_COLLECTED_TIME = "collectTime"
    const val X5_PUBLISH_TIME = "publishTime"
    const val X5_ARTICLE_TITLE = "title"
    const val X5_AUTHOR = "author"
    const val CHAPTER_ID = "chapterId"
    const val CHAPTER_NAME = "chapterName"
    const val SEARCH_KEY = "searchKey"

    // request code
    const val SETTING2LOGIN = 0x01

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
    const val wan_wxarticle_list = "wxarticle/list/" // wxarticle_list

    /**
     * 文章体系
     */
    const val wan_tree = "tree/json" // tree
    const val wan_tree_article = "article/list/" // index/json?cid=60

    /**
     * 收藏
     */
    const val wan_collect = "lg/collect/list/" // index/json: 'index' in url
    const val wan_collect_inner = "lg/collect/" // post "article_id" in url
    const val wan_collect_out = "lg/collect/add/json" // post title,author,link in body
    const val wan_unCollect_article = "lg/uncollect_originId/" // post "article_id" in url
    const val wan_unCollect = "lg/uncollect/" // post "article_id" in url, originId in body

    /**
     * 搜索
     */
    const val wan_hot_key = "hotkey/json" // hot_key
    const val wan_query = "article/query/" // index/post: 'index' in url, 'k' in body

    /**
     * 首页文章,Banner
     */
    const val wan_home_page = "article/list/" // {index}/json
    const val wan_banner = "banner/json"
}