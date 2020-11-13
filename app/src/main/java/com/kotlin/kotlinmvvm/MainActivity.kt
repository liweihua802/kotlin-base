package com.kotlin.kotlinmvvm

import android.widget.Toast
import com.base.library.adapter.RecyclerAdapter
import com.base.library.ui.BaseActivity
import com.kotlin.kotlinmvvm.databinding.ActivityMainBinding
import com.kotlin.kotlinmvvm.databinding.ItemArticleBinding
import com.kotlin.kotlinmvvm.logic.model.Article


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutId = R.layout.activity_main
    private var page = 0
    private val adapter by lazy {
        RecyclerAdapter<Article, ItemArticleBinding>(
            bind.recyclerView,
            R.layout.item_article,
            BR.article
        )
    }

    override fun onCreate() {
        enableLoadMore(adapter)
        vm.getArticlePage().observe(this) {
            if (it.errorCode == 0) {
                page++
                val articlePage = it.data!!
                adapter.setList(articlePage.datas, articlePage.curPage == 1, articlePage.over)
            } else {
                adapter.loadMoreModule.loadMoreFail()
                Toast.makeText(this, it.errorMsg, Toast.LENGTH_SHORT).show()
            }
            if (page == 1) finishRefresh(it.errorCode == 0)
        }
        onRefresh()
    }

    override fun onRefresh() {
        page = 0
        vm.getArticlePageList(page)
    }

    override fun onLoadMore() {
        vm.getArticlePageList(page)
    }

}