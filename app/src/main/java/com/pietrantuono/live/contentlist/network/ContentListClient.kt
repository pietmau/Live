package com.pietrantuono.live.contentlist.network

import com.pietrantuono.live.contentlist.pokos.ContentList
import com.pietrantuono.live.contentlist.pokos.Detail
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentListClient {

    suspend fun getResponse(): ContentList

    suspend fun getDetail(id: Int): Detail
}