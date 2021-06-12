package com.android.myanimelist.model.anime

import android.os.Parcelable
import com.android.myanimelist.model.base.Entity
import com.android.myanimelist.model.base.MalEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Anime data class.
 */
@Parcelize
data class Anime(
    /**
     * ID associated with MyAnimeList.
     */
    @SerializedName("mal_id")
    override val malId: Int,

    /**
     * Anime's MyAnimeList link.
     */
    @SerializedName("url")
    val url: String? = null,

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

    /**
     * Title of the anime in English.
     */
    @SerializedName("title_english")
    val titleEnglish: String? = null,

    /**
     * Title of the anime in Japanese.
     */
    @SerializedName("title_japanese")
    val titleJapanese: String? = null,

    /**
     * List of anime's synonyms.
     * @return null if there's none.
     */
    @SerializedName("title_synonyms")
    val titleSynonyms: List<String?>? = null,


    /**
     * Total episode(s) of the anime.
     */
    @SerializedName("episodes")
    val episodes: Int? = null,

    /**
     * Status of the anime (e.g "Airing", "Not yet airing", etc).
     */
    @SerializedName("status")
    val status: String? = null,

    /**
     * Whether the anime is currently airing or not.
     */
    @SerializedName("airing")
    val airing: Boolean? = null,


    /**
     * Duration per episode.
     */
    @SerializedName("duration")
    val duration: String? = null,

    /**
     * Age rating of the anime.
     */
    @SerializedName("rating")
    val rating: String? = null,

    /**
     * Score at MyAnimeList. Formatted up to 2 decimal places.
     */
    @SerializedName("score")
    val score: Double? = null,

    /**
     * Number of people/users that scored the anime.
     */
    @SerializedName("scored_by")
    val scoredBy: Int? = null,

    /**
     * Anime's score rank on MyAnimeList.
     */
    @SerializedName("rank")
    val rank: Int? = null,

    /**
     * Anime's popularity rank on MyAnimeList.
     */
    @SerializedName("popularity")
    val popularity: Int? = null,

    /**
     * Anime's members count on MyAnimeList.
     */
    @SerializedName("members")
    val members: Int? = null,

    /**
     * Anime's favorites count on MyAnimeList.
     */
    @SerializedName("favorites")
    val favorites: Int? = null,

    /**
     * Synopsis of the anime.
     */
    @SerializedName("synopsis")
    val synopsis: String? = null,

    /**
     * Background info of the anime.
     */
    @SerializedName("background")
    val background: String? = null,

    /**
     * Season where anime premiered.
     */
    @SerializedName("premiered")
    val premiered: String? = null,

    /**
     * Broadcast date of the anime (day and time).
     */
    @SerializedName("broadcast")
    val broadcast: String? = null,


    /**
     * List of opening theme (OP) of this anime.
     */
    @SerializedName("opening_themes")
    val openingThemes: List<String?>? = null,

    /**
     * List of ending theme (ED) of this anime.
     */
    @SerializedName("ending_themes")
    val endingThemes: List<String?>? = null

) : Entity(), MalEntity, Parcelable