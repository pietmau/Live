package com.pietrantuono.live.contentlist.network

import com.pietrantuono.live.contentlist.pokos.ContentList
import com.pietrantuono.live.contentlist.pokos.Detail
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentLitApi {

    @GET("contentList.json")
    suspend fun getContentList(): ContentList

    @GET("content/{id}.json")
    suspend fun getDetail(@Path("id") id: Int): Detail
}