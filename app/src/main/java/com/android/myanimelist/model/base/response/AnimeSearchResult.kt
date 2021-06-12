package com.android.myanimelist.model.base.response

import com.android.myanimelist.model.base.Entity
import com.android.myanimelist.model.base.types.AnimeTopEntity
import com.google.gson.annotations.SerializedName

/**
 * Result of search query.
 */
data class AnimeSearchResult(
    /**
     * List of anime result of search query.
     */
    @SerializedName("results")
    val results: List<AnimeTopEntity>,

    /**
     * Index of last page.
     */
    @SerializedName("last_page")
    val lastPage: Int? = null
) : Entity()