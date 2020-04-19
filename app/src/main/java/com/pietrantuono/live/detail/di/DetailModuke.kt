package com.pietrantuono.live.detail.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.detail.DetailViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object DetailModule {

    @JvmStatic
    @Provides
    fun provideViewModel(viewModelStoreOwner: FragmentActivity, factory: DetailViewModelFactory) =
        ViewModelProvider(viewModelStoreOwner, factory).get<DetailViewModel>(DetailViewModel::class.java)

    @JvmStatic
    @Provides
    fun provideDetailViewModelFactory(client: ContentListClient, @Named("id") id: Int) = DetailViewModelFactory(client, id)
}

class DetailViewModelFactory(val client: ContentListClient, val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailViewModel(client, id) as T
}