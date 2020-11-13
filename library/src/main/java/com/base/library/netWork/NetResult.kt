package com.base.library.netWork


/**
 * @时间 2020/10/30 15:46
 * @author liweihua
 * @描述 网络请求返回参数
 */
sealed class NetResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetResult<T>()
    data class Error(val code: Int, val msg: String) : NetResult<Nothing>()
}
