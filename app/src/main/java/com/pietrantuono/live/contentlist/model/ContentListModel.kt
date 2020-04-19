package com.pietrantuono.live.contentlist.model

import com.pietrantuono.live.contentlist.pokos.ContentList
import kotlinx.coroutines.flow.Flow

interface ContentListModel {

    val contentList: Flow<ContentList>
}