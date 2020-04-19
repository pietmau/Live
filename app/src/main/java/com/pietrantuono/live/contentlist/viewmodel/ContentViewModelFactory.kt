package com.pietrantuono.live.contentlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pietrantuono.live.contentlist.model.ContentListModel
import javax.inject.Inject

class ContentViewModelFactory @Inject constructor(val model: ContentListModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ContentListViewModel(model) as T
}