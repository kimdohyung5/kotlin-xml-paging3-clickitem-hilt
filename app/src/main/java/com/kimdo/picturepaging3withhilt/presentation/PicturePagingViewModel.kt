package com.kimdo.picturepaging3withhilt.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kimdo.picturepaging3withhilt.domain.repository.PicturePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PicturePagingViewModel @Inject constructor(private val repository: PicturePagingRepository) : ViewModel() {
    val pagingData = repository.getPagingData()
        .cachedIn(viewModelScope)
}