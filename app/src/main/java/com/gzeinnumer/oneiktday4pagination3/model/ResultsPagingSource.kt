package com.gzeinnumer.oneiktday4pagination3.model

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gzeinnumer.oneiktday4pagination3.network.RetroService
import org.w3c.dom.CharacterData

//todo 15
class ResultsPagingSource(val apiService: RetroService): PagingSource<Int, ResultsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {

        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, ResultsItem> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getDataFromAPI(nextPage)

            var nextPageNumber: Int? = null
            if(response.info.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            var prevPageNumber: Int? = null
            if(response.info.prev != null) {
                val uri = Uri.parse(response.info.prev)
                val prevPageQuery = uri.getQueryParameter("page")

                prevPageNumber = prevPageQuery?.toInt()
            }

            LoadResult.Page(data = response.results,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber)
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }



}