package com.pietrantuono.live.contentlist

import com.pietrantuono.live.contentlist.pokos.Item

sealed class ContentListViewState {
    object Loading : ContentListViewState()
    data class Error(val message: String? = null) : ContentListViewState()
    data class Content(val constntListItems: List<Item>) : ContentListViewState()
}