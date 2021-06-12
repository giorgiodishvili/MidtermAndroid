package com.android.myanimelist.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myanimelist.model.base.types.AnimeGeneralEntity
import com.android.myanimelist.pagination.search.AnimeSearchSubEntityRepo

class SearchViewModel : ViewModel() {

    private val animeRepo = AnimeSearchSubEntityRepo()

    fun searchAnime(searchWord: String): LiveData<PagingData<AnimeGeneralEntity>> {
        return animeRepo.searchAnime(searchWord).cachedIn(viewModelScope)
    }
}