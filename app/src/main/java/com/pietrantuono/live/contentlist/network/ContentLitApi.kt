package com.pietrantuono.live.contentlist.network

import com.pietrantuono.live.contentlist.pokos.ContentList
import retrofit2.http.GET

interface ContentLitApi {

    @GET("contentList.json")
    suspend fun getContentList(): ContentList
}