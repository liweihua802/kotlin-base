package com.kotlin.kotlinmvvm.logic.network

import com.kotlin.kotlinmvvm.logic.model.ArticlePage
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @时间 2020/11/11 11:11
 * @author liweihua
 * @包名 com.kotlin.kotlinmvvm.logic
 * @项目 KotlinMVVM
 * @描述
 */
interface WanService {
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): WanResult<ArticlePage>

}