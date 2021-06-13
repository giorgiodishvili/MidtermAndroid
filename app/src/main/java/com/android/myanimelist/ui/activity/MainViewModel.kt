package com.android.myanimelist.ui.activity

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.myanimelist.api.RetrofitService
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeGeneralEntity
import kotlinx.coroutines.*
import java.util.*

class MainViewModel : ViewModel() {

    private val fetchedAnimesByCategory =
        MutableLiveData<MutableMap<Int, MutableList<AnimeGeneralEntity>>>().apply {
            MutableLiveData<MutableMap<Int, MutableList<AnimeGeneralEntity>>>()
        }

    val _fetchedAnimesByCategory = fetchedAnimesByCategory

    private val animeMap: MutableMap<Int, MutableList<AnimeGeneralEntity>> = mutableMapOf()

    fun init() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val processes = async {
                    populateTempMap()
                }
                processes.await()
                fetchedAnimesByCategory.postValue(animeMap)
            }
        }
    }

    private suspend fun populateTempMap() {
        val topAnimeSubtypeIterator = TopSubtype.values().iterator()
        var elapsedSeconds: Long = 1200
        while (topAnimeSubtypeIterator.hasNext()) {
            val tStart = SystemClock.elapsedRealtime()

            if (elapsedSeconds < 1000) {
                delay(1000 - elapsedSeconds)
            }

            getAnimeByCategory(topAnimeSubtypeIterator.next())

            if (topAnimeSubtypeIterator.hasNext()) {
                getAnimeByCategory(topAnimeSubtypeIterator.next())
            }
            elapsedSeconds = SystemClock.elapsedRealtime() - tStart
        }
    }


    private suspend fun getAnimeByCategory(subType: TopSubtype) {
        if (subType.name == TopSubtype.NONE.name) {
            val topAnime = RetrofitService.RETROFIT.getTopAnime(1)
            val data = topAnime.body()
            animeMap[subType.ordinal] = data!!.general as MutableList<AnimeGeneralEntity>
        } else {

            val topAnime = RetrofitService.RETROFIT.getTopAnime(
                1,
                subType.name.lowercase(Locale.getDefault())
            )
            val data = topAnime.body()
            animeMap[subType.ordinal] = data!!.general as MutableList<AnimeGeneralEntity>
        }
    }


}