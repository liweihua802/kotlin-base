package com.base.library.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

/**
 * @时间 2020/10/30 16:38
 * @author liweihua
 * @描述 线程工具
 */
object ThreadUtils {
    private val HANDLER = Handler(Looper.getMainLooper())
    private val THREAD = Executors.newSingleThreadExecutor()

    /**
     * 主线程执行
     */
    fun runOnUiThread(runnable: Runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            HANDLER.post(runnable)
        }
    }

    /**
     * 单线程执行
     */
    fun runOnSingleThread(runnable: Runnable) {
        THREAD.execute(runnable)
    }
}

