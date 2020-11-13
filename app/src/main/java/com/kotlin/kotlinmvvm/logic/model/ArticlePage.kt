package com.kotlin.kotlinmvvm.logic.model

/**
 * @时间 2020/11/11 13:37
 * @author liweihua
 * @描述
 */
data class ArticlePage(val curPage: Int, val over: Boolean, val datas: List<Article>)
data class Article(val title: String)