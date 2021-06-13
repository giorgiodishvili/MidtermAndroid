package com.android.myanimelist.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.android.myanimelist.model.base.types.AnimeGeneralEntity
import com.android.myanimelist.pagination.search.AnimeSearchPagingSource

class SearchViewModel : ViewModel() {


    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun searchAnime(searchWord: String): LiveData<PagingData<AnimeGeneralEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { AnimeSearchPagingSource(searchWord) }
        ).liveData.cachedIn(viewModelScope)
    }
}