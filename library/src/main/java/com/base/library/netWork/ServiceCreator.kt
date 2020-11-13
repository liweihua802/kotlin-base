package com.base.library.netWork

import com.base.library.utils.LogUtils
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * @时间 2020/10/30 16:38
 * @author liweihua
 * @描述
 */
object ServiceCreator {
    private const val CONNECT_TIME_OUT = 60L
    private const val WRITE_TIME_OUT = 60L
    private const val READ_TIME_OUT = 60L

    fun <T> create(serviceClass: Class<T>, baseUrl: String, interceptor: Interceptor?): T {
        val builder = OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpInterceptor())
            .retryOnConnectionFailure(true)
        if (interceptor != null) {
            builder.addInterceptor(interceptor)
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }


    inline fun <reified T> create(baseUrl: String, interceptor: Interceptor? = null): T =
        create(T::class.java, baseUrl, interceptor)

    /**
     *拦截器，打印请求日志
     */
    class HttpInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
//            //添加token
//            val requestBuilder = original.newBuilder().method(original.method, original.body)
////            requestBuilder.addHeader("token", LoginHelper.token ?: "")
//            val request = requestBuilder.build()
            requestLog(request)
            val t1 = System.currentTimeMillis()
            val response = chain.proceed(request)
            val t2 = System.currentTimeMillis()
            val mediaType = response.body!!.contentType()
            val responseContent = response.body!!.string()
            responseLog(response.request.url, responseContent, t2 - t1)
            return response.newBuilder()
                .body(responseContent.toResponseBody(mediaType))
                .build()
        }

        /**
         * 打印请求日志
         */
        private fun requestLog(request: Request) {
            val buffer = Buffer()
            request.body?.writeTo(buffer)
            val body = buffer.readString(Charset.forName("UTF-8"))
            val log = "\nRequest Start->" +
                    "\nRequest URL=>${request.url}" +
                    "\nRequest method=>${request.method}" +
                    "\nRequest Headers=>\n${request.headers}" +
                    "\nRequest Body=>${body}" +
                    "\nRequest End<-"
            LogUtils.d(log)
        }

        /**
         * 打印返回信息日志
         */
        private fun responseLog(url: HttpUrl, responseContent: String, useTime: Long) {
            val log = "\nResponse Start->" +
                    "\nRequest URL=>${url}" +
                    "\nUse Time=>${useTime}ms" +
                    "\nResponse Body=>\n${responseContent}" +
                    "\nResponse End<-"
            LogUtils.d(log)
        }
    }
}