package com.pietrantuono.live.application

import com.pietrantuono.live.contentlist.di.ProdContentListSubComponent
import com.pietrantuono.live.contentlist.di.ContentListSubComponentModule
import com.pietrantuono.live.contentlist.di.ContentListSubComponent
import com.pietrantuono.live.detail.di.DetailSubComponent
import com.pietrantuono.live.detail.di.DetailSubComponentModule
import dagger.Component

@Component(modules = [ContentListSubComponentModule::class, DetailSubComponentModule::class, AppModule::class])
interface ProdAppComponent : AppComponent {

    override val contentListSubComponentFactory: ProdContentListSubComponent.ProdFactory
}

interface AppComponent {

    val contentListSubComponentFactory: ContentListSubComponent.Factory

    val detailSubComponentFactory: DetailSubComponent.Factory
}
