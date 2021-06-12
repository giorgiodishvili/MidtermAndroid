package com.android.myanimelist.model.base.response

import com.android.myanimelist.model.base.Entity
import com.android.myanimelist.model.base.types.AnimeGeneralEntity
import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(
    /**
     * List of top anime on MyAnimeList.
     */
    @SerializedName("top")
    val general: List<AnimeGeneralEntity>? = null
) : Entity()