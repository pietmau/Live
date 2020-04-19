package com.pietrantuono.live.contentlist.view

import com.pietrantuono.live.contentlist.pokos.ContentListItem

sealed class ContentListViewState {
    object Loading : ContentListViewState()
    data class Error(val message: String? = null) : ContentListViewState()
    data class Content(val constntListContentListItems: List<ContentListItem>) : ContentListViewState()
}