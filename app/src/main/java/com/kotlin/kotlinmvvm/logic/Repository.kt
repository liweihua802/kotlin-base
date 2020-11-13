package com.kotlin.kotlinmvvm.logic

import com.base.library.netWork.BaseRepository
import com.base.library.netWork.NetResult
import com.base.library.netWork.ServiceCreator
import com.kotlin.kotlinmvvm.logic.network.WanResult
import com.kotlin.kotlinmvvm.logic.network.WanService

/**
 * @时间 2020/11/10 15:02
 * @author liweihua
 * @描述
 */
object Repository : BaseRepository() {
    private val wanService = ServiceCreator.create<WanService>("https://www.wanandroid.com")

    /**
     * 转为自己服务器返回的固定结构
     */
    private suspend fun <T> handleWanResult(call: suspend () -> WanResult<T>) =
        when (val result = request { call() }) {
            is NetResult.Success -> result.data
            is NetResult.Error -> WanResult(errorCode = result.code, errorMsg = result.msg)
        }

    suspend fun getArticleList(page: Int) = handleWanResult { wanService.getArticleList(page) }

}