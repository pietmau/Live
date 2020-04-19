package com.pietrantuono.live.application

import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.network.RetrofitContentListClient
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindsContentListClient(client: RetrofitContentListClient): ContentListClient
}