package com.kotlin.kotlinmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.library.viewmodel.BaseViewModel
import com.kotlin.kotlinmvvm.logic.Repository
import com.kotlin.kotlinmvvm.logic.model.ArticlePage
import com.kotlin.kotlinmvvm.logic.network.WanResult
import kotlinx.coroutines.launch

/**
 * @时间 2020/11/10 16:41
 * @author liweihua
 * @描述
 */
class MainViewModel : BaseViewModel() {
    private val articlePage = MutableLiveData<WanResult<ArticlePage>>()
    fun getArticlePage(): LiveData<WanResult<ArticlePage>> = articlePage

    fun getArticlePageList(page: Int) {
        viewModelScope.launch {
            articlePage.value = request(false) { Repository.getArticleList(page) }
        }
    }
}