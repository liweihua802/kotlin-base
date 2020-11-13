package com.base.library.utils

import com.google.gson.Gson
import com.google.gson.JsonParser

/**
 * @时间 2020/11/9 14:23
 * @author liweihua
 * @描述 Gson工具类
 */
private val gson = Gson()

/**
 *任意对象转json字符串
 */
fun Any?.toJson(): String = gson.toJson(this)

/**
 * json字符串转对象
 */
fun <T> String?.fromJson(clz: Class<T>) =
    if (isNullOrEmpty()) {
        null
    } else {
        gson.fromJson(this, clz)
    }

/**
 * json字符串转对象list
 */
fun <T> String?.fromJsonList(clz: Class<T>) =
    if (isNullOrEmpty()) {
        emptyList()
    } else {
        val list = mutableListOf<T>()
        val array = JsonParser().parse(this).asJsonArray
        array.forEach { list.add(gson.fromJson(it, clz)) }
        list
    }

