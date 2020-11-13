package com.kotlin.kotlinmvvm

import android.widget.Toast
import com.base.library.adapter.RecyclerAdapter
import com.base.library.ui.BaseActivity
import com.base.library.utils.toast
import com.kotlin.kotlinmvvm.databinding.ActivityMainBinding
import com.kotlin.kotlinmvvm.databinding.ItemArticleBinding
import com.kotlin.kotlinmvvm.logic.model.Article
import com.kotlin.kotlinmvvm.logic.model.ArticlePage
import com.kotlin.kotlinmvvm.logic.network.WanResult


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
        vm.getArticlePage().observe(this, this::onGetArticle)
        onRefresh()
    }

    private fun onGetArticle(result: WanResult<ArticlePage>) {
        if (page == 0) finishRefresh(result.errorCode == 0)
        if (result.errorCode == 0) {
            page++
            val articlePage = result.data!!
            adapter.setList(articlePage.datas, articlePage.curPage == 1, !articlePage.over)
        } else {
            adapter.loadMoreModule.loadMoreFail()
            toast(result.errorMsg)
        }
    }

    override fun onRefresh() {
        page = 0
        vm.getArticlePageList(page)
    }

    override fun onLoadMore() {
        vm.getArticlePageList(page)
    }

}