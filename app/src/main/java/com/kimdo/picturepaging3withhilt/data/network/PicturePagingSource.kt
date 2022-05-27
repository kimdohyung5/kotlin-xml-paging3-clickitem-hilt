package com.kimdo.picturepaging3withhilt.data.network


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kimdo.picturepaging3withhilt.Constants
import com.kimdo.picturepaging3withhilt.domain.model.Picture
import java.lang.Exception

class PicturePagingSource (private val api: PictureApi) : PagingSource<Int, Picture>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {

        return try {

            val pageNumber = params.key ?: 1
            val response = api.getPictures(page = pageNumber, limit = Constants.PAGE_SIZE )

//            Log.d(TAG, "load: response=${response}")

            LoadResult.Page( data = response ,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = pageNumber + 1 )

        } catch( e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val TAG = "PicturePagingSource"
    }
}