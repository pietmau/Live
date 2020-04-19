package com.pietrantuono.live.contentlist.di

import androidx.appcompat.app.AppCompatActivity
import com.pietrantuono.live.contentlist.view.ContentListActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ContentListModule::class])
interface ContentListSubComponent {

    fun inject(contentListActivity: ContentListActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance appCompatActivity: AppCompatActivity): ContentListSubComponent
    }

}