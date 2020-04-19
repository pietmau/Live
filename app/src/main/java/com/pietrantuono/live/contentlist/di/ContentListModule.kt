package com.pietrantuono.live.contentlist.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.pietrantuono.live.contentlist.model.ContentListModel
import com.pietrantuono.live.contentlist.model.RetrofitContentListModel
import com.pietrantuono.live.contentlist.viewmodel.ContentViewModel
import com.pietrantuono.live.contentlist.viewmodel.ContentViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
abstract class ContentListModule {


    @Binds
    abstract fun bindsContentListModel(client: RetrofitContentListModel): ContentListModel

    companion object {

        @Provides
        fun provideViewModel(viewModelStoreOwner: FragmentActivity, factory: ContentViewModelFactory) =
            ViewModelProvider(viewModelStoreOwner, factory).get<ContentViewModel>(ContentViewModel::class.java)

        @Provides
        fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO
    }
}