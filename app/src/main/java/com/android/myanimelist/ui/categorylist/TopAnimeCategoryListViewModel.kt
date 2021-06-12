package com.android.myanimelist.ui.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myanimelist.model.base.types.AnimeTopEntity
import com.android.myanimelist.pagination.top.TopAnimeRepo

class TopAnimeCategoryListViewModel : ViewModel() {
    private val topAnimeRepo = TopAnimeRepo()

    fun getTopAnime(category: String): LiveData<PagingData<AnimeTopEntity>> {
        return topAnimeRepo.getTopAnime(category).cachedIn(viewModelScope)
    }
}