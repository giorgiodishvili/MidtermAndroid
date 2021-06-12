package com.android.myanimelist.pagination.top

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.myanimelist.api.RetrofitService
import com.android.myanimelist.model.base.types.AnimeTopEntity
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TopAnimePagingSource(private val category: String) :
    PagingSource<Int, AnimeTopEntity>() {


    override fun getRefreshKey(state: PagingState<Int, AnimeTopEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeTopEntity> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = RetrofitService.RETROFIT.getTopAnime(position,category)

            val data: List<AnimeTopEntity> = response.body()!!.top!!

            LoadResult.Page(
                data = data,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                nextKey = if (data.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
//            ApiResult.failure<NewsModel>("Error")
            return LoadResult.Error(exception)
        } catch (exception: NullPointerException) {
            return LoadResult.Error(exception)
        }
    }

}