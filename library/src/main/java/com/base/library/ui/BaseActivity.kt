package com.base.library.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.base.library.R
import com.base.library.adapter.RecyclerAdapter
import com.base.library.viewmodel.BaseViewModel
import com.base.library.widget.RefreshLayout
import com.lxj.xpopup.XPopup
import kotlin.reflect.KClass

/**
 * @时间 2020/11/2 10:16
 * @author liweihua
 * @描述 Activity基类
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var bind: DB
    protected lateinit var vm: VM
    private val loadingDialog by lazy { XPopup.Builder(this).asLoading() }
    private var refreshLayout: RefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, layoutId)
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
        vm.getLoading().observe(this) { loading(it) }
    }

    /**
     * 初始化下拉刷新控件
     */
    private fun initRefresh() {
        refreshLayout = findViewById(R.id.smart_refresh_layout)
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

    /**
     * 默认加载中弹窗
     */
    fun loading(show: Boolean) = if (show) showLoading() else hideLoading()

    /**
     * 显示加载中
     */
    private fun showLoading() {
        loadingDialog.show()
    }

    /**
     * 隐藏加载中
     */
    private fun hideLoading() {
        loadingDialog.dismiss()
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