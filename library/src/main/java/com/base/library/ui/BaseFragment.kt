package com.base.library.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.base.library.R
import com.base.library.adapter.RecyclerAdapter
import com.base.library.viewmodel.BaseViewModel
import com.base.library.widget.RefreshLayout
import kotlin.reflect.KClass

/**
 * @时间 2020/11/2 10:41
 * @author liweihua
 * @描述 Fragment基类
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    protected lateinit var bind: DB
    protected lateinit var vm: VM
    private var refreshLayout: RefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRefresh()
        onCreate()
    }

    /**
     * 初始化ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun initViewModel() {
        val modelClass = javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!!
        vm = ViewModelProvider(this).get((modelClass as KClass<VM>).java)
        vm.getLoading().observe(this) { (activity as BaseActivity<*, *>).loading(it) }
    }

    /**
     * 初始化下拉刷新控件
     */
    private fun initRefresh() {
        refreshLayout = bind.root.findViewById(R.id.smart_refresh_layout)
        refreshLayout?.setOnRefreshListener { onRefresh() }
    }

    /**
     * 下拉刷新操作
     */
    protected open fun onRefresh() {
    }

    /**
     * 刷新结束[success]刷新是否成功
     */
    protected fun finishRefresh(success: Boolean) {
        refreshLayout?.finishRefresh(success)
    }

    /**
     * 开启加载更多
     */
    protected fun enableLoadMore(adapter: RecyclerAdapter<*, *>) {
        adapter.loadMoreModule.run {
            isEnableLoadMore = true
            setOnLoadMoreListener { onLoadMore() }
        }
    }

    /**
     * 加载更多
     */
    protected open fun onLoadMore() {

    }

    /**抽象方法和抽象成员变量*/
    /**
     * 布局ID，必须是data binding layout
     */
    protected abstract val layoutId: Int

    /**
     * 初始化函数
     */
    protected abstract fun onCreate()
}