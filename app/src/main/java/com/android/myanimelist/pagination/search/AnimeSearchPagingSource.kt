package com.android.myanimelist.pagination.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.myanimelist.api.RetrofitService
import com.android.myanimelist.model.base.types.AnimeGeneralEntity
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class AnimeSearchPagingSource(private val searchWord: String) :
    PagingSource<Int, AnimeGeneralEntity>() {


    override fun getRefreshKey(state: PagingState<Int, AnimeGeneralEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeGeneralEntity> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = RetrofitService.RETROFIT.searchAnime(searchWord, position)

            var data: List<AnimeGeneralEntity>? = null

            data = response.body()!!.results


            LoadResult.Page(
                data = data,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                nextKey = if (data.lastIndex == position) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
//            ApiResult.failure<NewsModel>("Error")
            Log.i("binding", exception.toString())
            return LoadResult.Error(exception)
        } catch (exception: NullPointerException) {
            Log.i("binding null", exception.toString())
            return LoadResult.Error(exception)
        }
    }


}