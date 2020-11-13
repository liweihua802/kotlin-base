package com.kotlin.kotlinmvvm

import android.app.Application
import com.base.library.utils.LogUtils
import com.base.library.utils.Utils

/**
 * @时间 2020/11/12 14:00
 * @author liweihua
 * @描述
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(applicationContext)
        LogUtils.init(BuildConfig.DEBUG)
    }
}