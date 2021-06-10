package com.android.myanimelist.model.base

import com.android.myanimelist.model.base.types.AnimeTopEntity
import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(
    /**
     * List of top anime on MyAnimeList.
     */
    @SerializedName("top")
    val top: List<AnimeTopEntity>? = null
) : Entity()