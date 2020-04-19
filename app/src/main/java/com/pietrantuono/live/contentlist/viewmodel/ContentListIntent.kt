package com.pietrantuono.live.contentlist.viewmodel

sealed class ContentListIntent {
    data class OpenDetail(val id: Int) : ContentListIntent()
}