package com.pietrantuono.live.contentlist.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.pietrantuono.live.IntentConsumer
import com.pietrantuono.live.TransientEventProducer
import com.pietrantuono.live.ViewStatesProducer
import com.pietrantuono.live.contentlist.model.ContentListModel
import com.pietrantuono.live.contentlist.model.RetrofitContentListModel
import com.pietrantuono.live.contentlist.viewmodel.ContentListIntent
import com.pietrantuono.live.contentlist.viewmodel.ContentListTransientEvent
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState
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

    @Binds
    abstract fun bindsIntentConsumer(viewModel: ContentViewModel): IntentConsumer<ContentListIntent>

    @Binds
    abstract fun bindsViewStatesProducer(viewModel: ContentViewModel): ViewStatesProducer<ContentListViewState>

    @Binds
    abstract fun bindsTransientEventProducer(viewModel: ContentViewModel): TransientEventProducer<ContentListTransientEvent>

    companion object {

        @Provides
        fun provideViewModel(viewModelStoreOwner: FragmentActivity, factory: ContentViewModelFactory) =
            ViewModelProvider(viewModelStoreOwner, factory).get<ContentViewModel>(ContentViewModel::class.java)

        @Provides
        fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO
    }
}