package com.base.library.utils

import android.view.View
import android.view.ViewGroup

/**
 * @时间 2020/11/6 16:55
 * @author liweihua
 * @描述 View扩展函数
 */
fun View.removeParent(): View {
    if (parent != null) {
        (parent as ViewGroup).removeView(this)
    }
    return this
}