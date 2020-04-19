package com.pietrantuono.live.contentlist.viewmodel

import com.pietrantuono.live.Intent

sealed class ContentListIntent : Intent {
    data class OpenDetail(val id: Int) : ContentListIntent()
}