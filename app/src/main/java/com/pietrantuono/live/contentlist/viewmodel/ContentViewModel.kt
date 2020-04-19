package com.pietrantuono.live.contentlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.live.IntentConsumer
import com.pietrantuono.live.TransientEventProducer
import com.pietrantuono.live.ViewStatesProducer
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Error
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Content
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Loading
import com.pietrantuono.live.contentlist.viewmodel.ContentListTransientEvent.OpenDetail
import com.pietrantuono.live.contentlist.model.ContentListModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContentViewModel(private val model: ContentListModel, coroutineContext: CoroutineContext) : ViewModel(), IntentConsumer<ContentListIntent>,
    ViewStatesProducer<ContentListViewState>,
    TransientEventProducer<ContentListTransientEvent> {

    private val internalViewStates: MutableLiveData<ContentListViewState> = MutableLiveData(Loading)

    private val internalTransientEvents: TransientLiveData<ContentListTransientEvent> = TransientLiveData()

    override val viewStates: LiveData<ContentListViewState> = internalViewStates

    override val transientEvents: LiveData<ContentListTransientEvent> = internalTransientEvents

    init {
        viewModelScope.launch(coroutineContext) {
            model.contentList
                .catch { emitViewState(Error(it.localizedMessage)) }
                .map { (it.contentListItems ?: emptyList()).sortedBy { it.title } }
                .collect { emitViewState(Content(it)) }
        }
    }

    override fun acceptIntent(intent: ContentListIntent) =
        when (intent) {
            is ContentListIntent.OpenDetail -> onOpenDetail(intent)
        }

    private fun onOpenDetail(intent: ContentListIntent.OpenDetail) {
        val item = getItemById(intent)
        internalTransientEvents.postValue(OpenDetail(item))
    }

    private fun getItemById(intent: ContentListIntent.OpenDetail) =
        requireNotNull((internalViewStates.value as Content).listContentListItems.find { it.id == intent.id })

    private fun emitViewState(state: ContentListViewState) = internalViewStates.postValue(state)

}