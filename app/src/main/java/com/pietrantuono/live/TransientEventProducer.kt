package com.pietrantuono.live

import androidx.lifecycle.LiveData
import com.pietrantuono.live.contentlist.viewmodel.ContentListTransientEvent

interface TransientEventProducer<T : TransientEvent> {

    val transientEvents: LiveData<T>
}

interface TransientEvent