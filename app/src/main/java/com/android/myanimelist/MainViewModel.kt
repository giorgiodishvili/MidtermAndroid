package com.android.myanimelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.myanimelist.api.RetrofitService
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeTopEntity
import kotlinx.coroutines.*
import java.util.*

class MainViewModel : ViewModel() {

    private val fetchedAnimesByCategory =
        MutableLiveData<MutableMap<Int, MutableList<AnimeTopEntity>>>().apply {
            MutableLiveData<MutableMap<Int, MutableList<AnimeTopEntity>>>()
        }

    val _fetchedAnimesByCategory = fetchedAnimesByCategory

    private val animeMap: MutableMap<Int, MutableList<AnimeTopEntity>> = mutableMapOf()





    fun init() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val processes1 = async {
                    populate(TopSubtype.TV)
                }
                val processes2 = async {
                    populate(TopSubtype.AIRING)
                }
                processes1.await()
                processes2.await()
                delay(2100)
                val processes3 = async {
                    populate(TopSubtype.MOVIE)
                }
                val processes4 = async {
                    populate(TopSubtype.SPECIAL)
                }
                processes3.await()
                processes4.await()
                delay(2100)
                val processes5 = async {
                    populate(TopSubtype.UPCOMING)
                }

                val processes6 = async {
                    populate(TopSubtype.OVA)
                }
                processes5.await()
                processes6.await()
                val processes7 = async {
                    populate(TopSubtype.NONE)
                }
                processes7.await()
                fetchedAnimesByCategory.postValue(animeMap)
            }
        }
    }


    private suspend fun populate(subType: TopSubtype) {
        if (subType.name == "NONE") {
            val topAnime = RetrofitService.RETROFIT.getTopAnime(1)
            val data = topAnime.body()
            animeMap[subType.ordinal] = data!!.top as MutableList<AnimeTopEntity>
        } else {

            val topAnime = RetrofitService.RETROFIT.getTopAnime(
                1,
                subType.name.lowercase(Locale.getDefault())
            )
            val data = topAnime.body()
            animeMap[subType.ordinal] = data!!.top as MutableList<AnimeTopEntity>
        }
    }




}