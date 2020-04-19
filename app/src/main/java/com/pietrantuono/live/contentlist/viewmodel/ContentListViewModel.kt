package com.pietrantuono.live.contentlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.live.contentlist.view.ContentListViewState
import com.pietrantuono.live.contentlist.view.ContentListViewState.Error
import com.pietrantuono.live.contentlist.view.ContentListViewState.Content
import com.pietrantuono.live.contentlist.view.ContentListViewState.Loading

import com.pietrantuono.live.contentlist.model.ContentListModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ContentListViewModel(private val model: ContentListModel) : ViewModel() {

    private val internalViewStates: MutableLiveData<ContentListViewState> = MutableLiveData(Loading)

    val viewStates: LiveData<ContentListViewState> = internalViewStates

    init {
        viewModelScope.launch {
            model.contentList
                .onStart { emitViewState(Loading) }
                .catch { emitViewState(Error(it.localizedMessage)) }
                .map { (it.contentListItems ?: emptyList()).sortedBy { it.title } }
                .collect { emitViewState(Content(it)) }
        }
    }

    private fun emitViewState(state: ContentListViewState) = internalViewStates.postValue(state)
}