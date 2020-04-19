package com.pietrantuono.live.contentlist.network

import com.pietrantuono.live.contentlist.pokos.ContentList

interface ContentListClient {

    suspend fun getResponse(): ContentList
}