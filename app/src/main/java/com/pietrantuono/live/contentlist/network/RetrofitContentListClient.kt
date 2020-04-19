package com.pietrantuono.live.contentlist.network

import com.pietrantuono.live.contentlist.pokos.ContentList
import com.pietrantuono.live.contentlist.pokos.Detail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val BASE_URL = "https://dynamic.pulselive.com/test/native/"

class RetrofitContentListClient @Inject constructor() : ContentListClient {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<ContentLitApi>(ContentLitApi::class.java)

    override suspend fun getResponse(): ContentList = api.getContentList()

    override suspend fun getDetail(id: Int): Detail = api.getDetail(id)
}