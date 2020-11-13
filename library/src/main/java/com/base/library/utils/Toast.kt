package com.base.library.utils

import android.widget.Toast

/**
 * @时间 2020/11/12 13:46
 * @author liweihua
 * @描述
 */
private var toast: Toast? = null

fun toast(text: String) {
    toast?.cancel()
    toast = Toast.makeText(Utils.context, text, Toast.LENGTH_SHORT)
    toast?.show()
}