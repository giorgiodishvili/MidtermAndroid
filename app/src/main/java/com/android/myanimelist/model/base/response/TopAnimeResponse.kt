package com.android.myanimelist.model.base.response

import com.android.myanimelist.model.base.Entity
import com.android.myanimelist.model.base.types.AnimeTopEntity
import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(
    /**
     * List of top anime on MyAnimeList.
     */
    @SerializedName("top")
    val top: List<AnimeTopEntity>? = null
) : Entity()