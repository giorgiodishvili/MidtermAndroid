package com.android.myanimelist.api


import com.android.myanimelist.model.anime.Anime
import com.android.myanimelist.model.base.response.AnimeSearchResult
import com.android.myanimelist.model.base.response.TopAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AnimeApi {
//
//    @GET("top/anime/{page}/movie")
//    suspend fun getTopAnimeMovie(@Path("page") page: Int = 1): Response<List<List<*>>>
//
//    @GET("top/anime/{page}/airing")
//    suspend fun getTopAnimeAiring(@Path("page") page: Int = 1): Response<List<List<*>>>
//
//    @GET("top/anime/{page}/upcoming")
//    suspend fun getTopAnimeUpcoming(@Path("page") page: Int = 1): Response<List<List<*>>>
//
//    @GET("top/anime/{page}/tv")
//    suspend fun getTopAnimeTv(@Path("page") page: Int = 1): Response<List<List<*>>>
//
//    @GET("top/anime/{page}/ova")
//    suspend fun getTopAnimeOva(@Path("page") page: Int = 1): Response<List<List<*>>>
//
//    @GET("top/anime/{page}/special")
//    suspend fun getTopAnimeSpecial(@Path("page") page: Int = 1): Response<List<List<*>>>


    /**
     * Function to get all top anime on MyAnimeList.
     * @param subtype: Optional, subtype type (upcoming, airing, etc).
     * @param page: Optional, default is 1. Index of page, each page contain 50 items.
     * @return List of top anime on MyAnimeList.
     */
    @GET("top/anime/{page}/{subType}/")
    suspend fun getTopAnime(
        @Path("page") page: Int? = 1,
        @Path("subType") subtype: String? = ""
    ): Response<TopAnimeResponse>

    @GET("anime/{malId}/")
    suspend fun getAnime(
        @Path("malId") malId: Int
    ): Response<Anime>

    @GET("search/anime/")
    suspend fun searchAnime(
        @Query("q") searchKeyWord: String,
        @Query("page") page: Int
    ): Response<AnimeSearchResult>
}