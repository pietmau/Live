package com.pietrantuono.live.detail.di

import androidx.fragment.app.FragmentActivity
import com.pietrantuono.live.detail.DetailFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {

    fun inject(detailFragment: DetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance appCompatActivity: FragmentActivity, @BindsInstance @Named("id") id: Int): DetailSubComponent
    }
}