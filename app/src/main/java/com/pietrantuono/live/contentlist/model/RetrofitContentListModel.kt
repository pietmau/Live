package com.pietrantuono.live.contentlist.model

import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.pokos.ContentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RetrofitContentListModel @Inject constructor(private val contentListClient: ContentListClient) : ContentListModel {

    override val contentList: Flow<ContentList> = flow {
        emit(contentListClient.getResponse())
    }
}