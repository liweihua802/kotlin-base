package com.base.library.utils

import android.content.Context

/**
 * @时间 2020/11/12 13:51
 * @author liweihua
 * @包名 com.base.library.utils
 * @项目 KotlinMVVM
 * @描述 必须初始化
 */
object Utils {
    private var initContext: Context? = null

    val context: Context
        get() {
            return if (initContext == null) {
                throw NullPointerException("context未初始化")
            } else {
                initContext!!
            }
        }

    fun init(context: Context) {
        initContext = context
    }
}