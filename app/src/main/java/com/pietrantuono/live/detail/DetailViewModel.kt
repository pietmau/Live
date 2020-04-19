package com.pietrantuono.live.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.pokos.DetailItem
import kotlinx.coroutines.launch

class DetailViewModel(private val client: ContentListClient, private val id: Int) : ViewModel() {

    private val internalLiveData: MutableLiveData<DetailItem> by lazy { MutableLiveData<DetailItem>() }

    val liveData: LiveData<DetailItem> = internalLiveData

    init {
        viewModelScope.launch {
            internalLiveData.postValue(client.getDetail(id).detailItem)
        }
    }
}