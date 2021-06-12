package com.android.myanimelist.model.base.types

import android.os.Parcelable
import com.android.myanimelist.model.base.MalEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeGeneralEntity(
    /**
     * ID associated with MyAnimeList.
     */
    @SerializedName("mal_id")
    override val malId: Int,

    /**
     * Anime's MyAnimeList cover/image link.
     */
    @SerializedName("image_url")
    val imageUrl: String? = null,

    /**
     * Title of the anime.
     */
    @SerializedName("title")
    val title: String? = null,


    ) : MalEntity, Parcelable {


}
