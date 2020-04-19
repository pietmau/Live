package com.pietrantuono.live.contentlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pietrantuono.live.contentlist.model.ContentListModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ContentViewModelFactory @Inject constructor(
    val model: ContentListModel,
    val coroutineContext: CoroutineContext
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ContentViewModel(model, coroutineContext) as T
}