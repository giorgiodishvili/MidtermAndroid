package com.android.myanimelist.model.anime

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.myanimelist.api.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeViewModel : ViewModel() {
    private val fetchedAnime =
        MutableLiveData<Anime>().apply {
            MutableLiveData<Anime>()
        }

    val _fetchedfetchedAnime = fetchedAnime


    fun getAnime(malId: Int) {
        Log.i("onClikkk", "getAnime() $malId")

        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val topAnime = RetrofitService.RETROFIT.getAnime(malId)
                if (topAnime.isSuccessful) {
                    val data = topAnime.body()
                    fetchedAnime.postValue(data)
                } else {
                    Log.i("onClikkk", topAnime.message())
                }
            }
        }
    }


}