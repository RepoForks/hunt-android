package com.ctech.eaty.ui.home.result

import com.ctech.eaty.base.redux.Result
import com.ctech.eaty.ui.home.model.Cursor
import com.ctech.eaty.ui.home.viewmodel.UpcomingProductItemProps

data class LoadUpcomingProductResult(val loading: Boolean = false, val error: Throwable? = null,
                                     val page: Int = 1,
                                     val content: List<UpcomingProductItemProps> = emptyList()) : Result {
    companion object {
        fun inProgress(): LoadUpcomingProductResult {
            return LoadUpcomingProductResult(true)
        }

        fun success(content: List<UpcomingProductItemProps>, page: Int): LoadUpcomingProductResult {
            return LoadUpcomingProductResult(content = content, page = page)
        }

        fun fail(throwable: Throwable): LoadUpcomingProductResult {
            return LoadUpcomingProductResult(error = throwable)
        }
    }
}