package com.pietrantuono.live

import androidx.lifecycle.LiveData
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState

interface ViewStatesProducer<T : ViewState> {
    val viewStates: LiveData<T>
}

interface ViewState