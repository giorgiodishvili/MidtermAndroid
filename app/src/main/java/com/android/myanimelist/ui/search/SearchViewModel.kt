package com.android.myanimelist.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myanimelist.model.base.types.AnimeSearchSubEntity
import com.android.myanimelist.pagination.AnimeSearchSubEntityRepo

class SearchViewModel : ViewModel() {

    private val animeRepo = AnimeSearchSubEntityRepo()

    private var downloadingLiveData = MutableLiveData<Boolean>()

    fun searchAnime(searchWord: String): LiveData<PagingData<AnimeSearchSubEntity>> {
        return animeRepo.searchAnime(searchWord).cachedIn(viewModelScope)
    }
}