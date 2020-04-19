package com.pietrantuono.live.contentlist.viewmodel

import com.pietrantuono.live.ViewState
import com.pietrantuono.live.contentlist.pokos.ContentListItem

sealed class ContentListViewState:ViewState {
    object Loading : ContentListViewState()
    data class Error(val message: String? = null) : ContentListViewState()
    data class Content(val listContentListItems: List<ContentListItem>) : ContentListViewState()
}