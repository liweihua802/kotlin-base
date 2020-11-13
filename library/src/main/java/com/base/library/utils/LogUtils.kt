package com.base.library.utils

import android.util.Log
import java.util.*

/**
 * @时间 2020/10/30 16:38
 * @author liweihua
 * @描述 日志打印工具，默认不打印日志
 */
object LogUtils {
    private var IS_SHOW_LOG = false
    private const val DEFAULT_MESSAGE = "execute"
    private const val V = 0x1
    private const val D = 0x2
    private const val I = 0x3
    private const val W = 0x4
    private const val E = 0x5
    private const val A = 0x6

    fun init(showLog: Boolean) {
        IS_SHOW_LOG = showLog
    }

    fun v() {
        printLog(V, null, DEFAULT_MESSAGE)
    }

    fun v(msg: Any?) {
        printLog(V, null, msg)
    }

    fun v(tag: String?, msg: String?) {
        printLog(V, tag, msg)
    }

    fun d() {
        printLog(D, null, DEFAULT_MESSAGE)
    }

    fun d(msg: Any?) {
        printLog(D, null, msg)
    }

    fun d(tag: String?, msg: Any?) {
        printLog(D, tag, msg)
    }

    fun i() {
        printLog(I, null, DEFAULT_MESSAGE)
    }

    fun i(msg: Any?) {
        printLog(I, null, msg)
    }

    fun i(tag: String?, msg: Any?) {
        printLog(I, tag, msg)
    }

    fun w() {
        printLog(W, null, DEFAULT_MESSAGE)
    }

    fun w(msg: Any?) {
        printLog(W, null, msg)
    }

    fun w(tag: String?, msg: Any?) {
        printLog(W, tag, msg)
    }

    fun e() {
        printLog(E, null, DEFAULT_MESSAGE)
    }

    fun e(msg: Any?) {
        printLog(E, null, msg)
    }

    fun e(tag: String?, msg: Any?) {
        printLog(E, tag, msg)
    }

    fun a() {
        printLog(A, null, DEFAULT_MESSAGE)
    }

    fun a(msg: Any?) {
        printLog(A, null, msg)
    }

    fun a(tag: String?, msg: Any?) {
        printLog(A, tag, msg)
    }

    private fun printLog(type: Int, tagStr: String?, objectMsg: Any?) {
        if (!IS_SHOW_LOG) {
            return
        }
        val stackTrace = Thread.currentThread().stackTrace
        val index = 4
        val className = stackTrace[index].fileName
        var methodName = stackTrace[index].methodName
        val lineNumber = stackTrace[index].lineNumber
        val tag = tagStr ?: className
        methodName = methodName.substring(0, 1).toUpperCase(Locale.ROOT) + methodName.substring(1)
        val stringBuilder = StringBuilder()
        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#")
            .append(methodName).append(" ] ")
        val msg = objectMsg?.toString() ?: "Log with null Object"
        stringBuilder.append(msg)
        val logStr = stringBuilder.toString()
        when (type) {
            V -> Log.v(tag, logStr)
            D -> Log.d(tag, logStr)
            I -> Log.i(tag, logStr)
            W -> Log.w(tag, logStr)
            E -> Log.e(tag, logStr)
            A -> Log.wtf(tag, logStr)
        }
    }
}