package com.pietrantuono.live.contentlist.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pietrantuono.live.contentlist.model.ContentListModel
import com.pietrantuono.live.contentlist.model.RetrofitContentListModel
import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.network.RetrofitContentListClient
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewModel
import com.pietrantuono.live.contentlist.viewmodel.ContentViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
abstract class ContentListModule {

    @Binds
    abstract fun bindsContentListClient(client: RetrofitContentListClient): ContentListClient

    @Binds
    abstract fun bindsContentListModel(client: RetrofitContentListModel): ContentListModel

    companion object {

        @Provides
        fun provideViewModel(viewModelStoreOwner: AppCompatActivity, factory: ContentViewModelFactory) =
            ViewModelProvider(viewModelStoreOwner, factory).get<ContentListViewModel>(ContentListViewModel::class.java)

        @Provides
        fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO
    }
}