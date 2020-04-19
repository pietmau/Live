package com.pietrantuono.live.contentlist.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.pietrantuono.live.contentlist.view.ContentListActivity
import com.pietrantuono.live.detail.DetailFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ContentListModule::class])
interface ContentListSubComponent {

    fun inject(contentListActivity: ContentListActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance appCompatActivity: FragmentActivity): ContentListSubComponent
    }
}