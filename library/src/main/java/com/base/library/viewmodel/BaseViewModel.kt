package com.base.library.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @时间 2020/11/2 10:17
 * @author liweihua
 * @描述 ViewModel基类封装
 */
open class BaseViewModel : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    fun getLoading(): LiveData<Boolean> = loading

    /**
     * 发起网路请求,切换至IO
     */
    protected suspend fun <R> request(
        showLoading: Boolean = true,
        block: suspend () -> R
    ): R {
        if (showLoading) {
            loading.value = true
        }
        val result = withContext(Dispatchers.IO) { block() }
        if (showLoading) {
            loading.value = false
        }
        return result
    }

}