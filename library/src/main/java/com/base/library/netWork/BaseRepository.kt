package com.base.library.netWork

import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import kotlinx.coroutines.coroutineScope
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @时间 2020/10/30 16:41
 * @author liweihua
 * @描述
 */
open class BaseRepository {
    /**
     * 发起网络请求
     */
    protected suspend fun <T : Any> request(call: suspend () -> T): NetResult<T> {
        return coroutineScope {
            try {
                NetResult.Success(call())
            } catch (e: Exception) {
                handlerException(e)
            }
        }
    }

    /**
     * 异常处理
     */
    private fun handlerException(t: Throwable) =
        when (t) {
            is SocketTimeoutException,
            is ConnectException,
            is UnknownHostException -> errorResult(100, "网络异常")
            is JsonParseException,
            is JSONException,
            is ParseException,
            is NumberFormatException,
            is MalformedJsonException -> errorResult(101, "数据解析出错")
            is NullPointerException -> errorResult(102, "客户端开小差了，攻城狮正在修复中...")
            is HttpException -> errorResult(t.code(), t.toString())
            else -> errorResult(110, t.message ?: "未知错误")
        }

    /**
     * 异常result
     */
    private fun errorResult(code: Int, msg: String) = NetResult.Error(code, msg)
}