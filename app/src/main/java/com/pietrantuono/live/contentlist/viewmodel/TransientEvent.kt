package com.pietrantuono.live.contentlist.viewmodel

import com.pietrantuono.live.contentlist.pokos.ContentListItem

sealed class TransientEvent {
    data class OpenDatail(val detailItem: ContentListItem) : TransientEvent()
}