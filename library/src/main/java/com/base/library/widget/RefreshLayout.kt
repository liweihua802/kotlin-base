package com.base.library.widget

import android.content.Context
import android.util.AttributeSet
import com.base.library.R
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @时间 2020/11/2 14:39
 * @author liweihua
 * @描述 下拉刷新，默认经典刷新头，不启用加载更多
 */
class RefreshLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SmartRefreshLayout(context, attrs) {

    init {
        //设置默认id
        id = R.id.smart_refresh_layout
        //设置经典刷新头
        setRefreshHeader(ClassicsHeader(context))
        //是否启用越界拖动
        setEnableOverScrollDrag(true)
        //是否启用上拉加载功能
        setEnableLoadMore(false)
        //是否在列表不满一页时候开启上拉加载功能
        setEnableLoadMoreWhenContentNotFull(false)
    }
}