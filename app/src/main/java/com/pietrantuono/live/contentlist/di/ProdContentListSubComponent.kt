package com.pietrantuono.live.contentlist.di

import androidx.fragment.app.FragmentActivity
import com.pietrantuono.live.contentlist.view.ContentListActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ContentListModule::class])
interface ProdContentListSubComponent:ContentListSubComponent {

    @Subcomponent.Factory
    interface ProdFactory: ContentListSubComponent.Factory {
        override fun create(@BindsInstance appCompatActivity: FragmentActivity): ProdContentListSubComponent
    }
}

interface ContentListSubComponent {

    fun inject(contentListActivity: ContentListActivity)


    interface Factory {
        fun create( appCompatActivity: FragmentActivity): ContentListSubComponent
    }
}
