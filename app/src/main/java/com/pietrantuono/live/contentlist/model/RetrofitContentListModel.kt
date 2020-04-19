package com.pietrantuono.live.contentlist.model

import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.pokos.ContentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RetrofitContentListModel @Inject constructor(
    private val contentListClient: ContentListClient,
    val coroutineContext: CoroutineContext
) : ContentListModel {

    override val contentList: Flow<ContentList> = flow {
        emit(contentListClient.getResponse())
    }.flowOn(coroutineContext)
}