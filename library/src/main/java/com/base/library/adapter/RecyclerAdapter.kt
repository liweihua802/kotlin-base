package com.base.library.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @时间 2020/11/6 16:34
 * @author liweihua
 * @描述 基于BaseQuickAdapter封装的单布局适配器，使用DataBinding
 */
class RecyclerAdapter<T, DB : ViewDataBinding>(
    recyclerView: RecyclerView,
    layoutResId: Int,
    private val variableId: Int = -1
) : BaseQuickAdapter<T, BaseDataBindingHolder<DB>>(layoutResId), LoadMoreModule {

    init {
        this.recyclerView = recyclerView
        recyclerView.adapter = this
    }

    /**
     * 添加数据，用于分页加载
     * [list]要添加的数据
     * [firstPage]当前是否第一页
     * [hasMore]是否还有更多
     */
    fun setList(list: Collection<T>?, firstPage: Boolean, hasMore: Boolean = false) {
        if (firstPage) setList(list) else addData(list ?: emptyList())
        loadMoreModule.run {
            if (isEnableLoadMore) {
                if (hasMore) loadMoreComplete() else loadMoreEnd()
            }
        }
    }

    /**
     * 视图绑定
     */
    override fun convert(holder: BaseDataBindingHolder<DB>, item: T) {
        if (variableId != -1) {
            holder.dataBinding?.setVariable(variableId, item)
            holder.dataBinding?.executePendingBindings()
        }
    }
}