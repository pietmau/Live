package com.pietrantuono.live.application

import com.pietrantuono.live.contentlist.di.ContentListSubComponent
import com.pietrantuono.live.contentlist.di.ContentListSubComponentModule
import dagger.Component

@Component(modules = [ContentListSubComponentModule::class])
interface AppComponent {

    val contentListSubComponentFactory: ContentListSubComponent.Factory
}