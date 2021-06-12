package com.android.myanimelist.pagination

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.android.myanimelist.model.base.types.AnimeSearchSubEntity

class AnimeSearchSubEntityRepo {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun searchAnime(searchWord: String): LiveData<PagingData<AnimeSearchSubEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { AnimeSearchPagingSource(searchWord) }
        ).liveData
    }

}