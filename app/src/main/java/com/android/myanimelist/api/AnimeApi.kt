package com.android.myanimelist.api



import com.android.myanimelist.model.base.TopAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimeApi {

    @GET("top/anime/{page}/movie")
    suspend fun getTopAnimeMovie(@Path("page") page: Int = 1): Response<List<List<*>>>

    @GET("top/anime/{page}/airing")
    suspend fun getTopAnimeAiring(@Path("page") page: Int = 1): Response<List<List<*>>>

    @GET("top/anime/{page}/upcoming")
    suspend fun getTopAnimeUpcoming(@Path("page") page: Int = 1): Response<List<List<*>>>

    @GET("top/anime/{page}/tv")
    suspend fun getTopAnimeTv(@Path("page") page: Int = 1): Response<List<List<*>>>

    @GET("top/anime/{page}/ova")
    suspend fun getTopAnimeOva(@Path("page") page: Int = 1): Response<List<List<*>>>

    @GET("top/anime/{page}/special")
    suspend fun getTopAnimeSpecial(@Path("page") page: Int = 1): Response<List<List<*>>>


    /**
     * Function to get all top anime on MyAnimeList.
     * @param subtype: Optional, subtype type (upcoming, airing, etc).
     * @param page: Optional, default is 1. Index of page, each page contain 50 items.
     * @return List of top anime on MyAnimeList.
     */
    @GET("top/anime/{page}/{subType}/")
    suspend fun getTopAnime(@Path("page") page: Int? = 1, @Path("subType") subtype: String? = ""): Response<TopAnimeResponse>
}