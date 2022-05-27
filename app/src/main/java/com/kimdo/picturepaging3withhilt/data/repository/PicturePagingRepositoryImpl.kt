package com.kimdo.picturepaging3withhilt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kimdo.picturepaging3withhilt.Constants
import com.kimdo.picturepaging3withhilt.data.network.PictureApi
import com.kimdo.picturepaging3withhilt.data.network.PicturePagingSource
import com.kimdo.picturepaging3withhilt.domain.model.Picture
import com.kimdo.picturepaging3withhilt.domain.repository.PicturePagingRepository
import kotlinx.coroutines.flow.Flow

class PicturePagingRepositoryImpl constructor(private val api: PictureApi) : PicturePagingRepository {
    override fun getPagingData(): Flow<PagingData<Picture>> {
        return Pager( PagingConfig(pageSize = Constants.PAGE_SIZE)) {
            PicturePagingSource(api)
        }.flow
    }
}