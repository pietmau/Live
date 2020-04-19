package com.pietrantuono.live.contentlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.live.contentlist.ContentListViewState
import com.pietrantuono.live.contentlist.ContentListViewState.Error
import com.pietrantuono.live.contentlist.ContentListViewState.Content

import com.pietrantuono.live.contentlist.model.ContentListModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContentListViewModel(private val model: ContentListModel) : ViewModel() {

    private val internalViewStates: MutableLiveData<ContentListViewState> = MutableLiveData(ContentListViewState.Loading)

    val viewStates: LiveData<ContentListViewState> = internalViewStates

    init {
        viewModelScope.launch {
            model.contentList
                .catch { emitViewState(Error(it.localizedMessage)) }
                .collect { emitViewState(Content(it.items ?: emptyList())) }
        }
    }

    private fun emitViewState(state: ContentListViewState) = internalViewStates.postValue(state)
}