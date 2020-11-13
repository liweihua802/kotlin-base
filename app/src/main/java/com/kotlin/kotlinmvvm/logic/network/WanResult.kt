package com.kotlin.kotlinmvvm.logic.network

/**
 * @时间 2020/11/11 11:12
 * @author liweihua
 * @描述
 */
data class WanResult<T>(val errorCode: Int, val errorMsg: String, val data: T? = null)