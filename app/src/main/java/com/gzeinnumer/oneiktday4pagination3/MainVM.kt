package com.gzeinnumer.oneiktday4pagination3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gzeinnumer.oneiktday4pagination3.model.ResultsPagingSource
import com.gzeinnumer.oneiktday4pagination3.model.ResultsItem
import com.gzeinnumer.oneiktday4pagination3.network.RetroInstance
import com.gzeinnumer.oneiktday4pagination3.network.RetroService
import kotlinx.coroutines.flow.Flow
import org.w3c.dom.CharacterData

//todo 16
class MainVM : ViewModel() {

    lateinit var retroService: RetroService

    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<ResultsItem>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {ResultsPagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }
}