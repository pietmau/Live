package com.pietrantuono.live.contentlist.viewmodel

import com.pietrantuono.live.TransientEvent
import com.pietrantuono.live.contentlist.pokos.ContentListItem

sealed class ContentListTransientEvent:TransientEvent {
    data class OpenDetail(val detailItem: ContentListItem) : ContentListTransientEvent()
}