package com.kimdo.picturepaging3withhilt.domain.repository


import androidx.paging.PagingData
import com.kimdo.picturepaging3withhilt.domain.model.Picture
import kotlinx.coroutines.flow.Flow

interface PicturePagingRepository {
    fun getPagingData(): Flow<PagingData<Picture>>
}