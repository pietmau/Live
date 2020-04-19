package com.pietrantuono.live.contentlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Error
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Content
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Loading
import com.pietrantuono.live.contentlist.viewmodel.TransientEvent.OpenDatail
import com.pietrantuono.live.contentlist.model.ContentListModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ContentListViewModel(private val model: ContentListModel) : ViewModel() {

    private val internalViewStates: MutableLiveData<ContentListViewState> = MutableLiveData(Loading)

    private val internalTransientEvents: TransientLiveData<TransientEvent> = TransientLiveData()

    val viewStates: LiveData<ContentListViewState> = internalViewStates

    val transientEvents: LiveData<TransientEvent> = internalTransientEvents

    init {
        viewModelScope.launch {
            model.contentList
                .onStart { emitViewState(Loading) }
                .catch { emitViewState(Error(it.localizedMessage)) }
                .map { (it.contentListItems ?: emptyList()).sortedBy { it.title } }
                .collect { emitViewState(Content(it)) }
        }
    }

    fun acceptIntent(intent: ContentListIntent) =
        when (intent) {
            is ContentListIntent.OpenDetail -> onOpenDetail(intent)
        }

    private fun onOpenDetail(intent: ContentListIntent.OpenDetail) {
        val item = getItemById(intent)
        internalTransientEvents.postValue(OpenDatail(item))
    }

    private fun getItemById(intent: ContentListIntent.OpenDetail) =
        requireNotNull((internalViewStates.value as Content).constntListContentListItems.find { it.id == intent.id })

    private fun emitViewState(state: ContentListViewState) = internalViewStates.postValue(state)

}