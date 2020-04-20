package com.pietrantuono.live

import androidx.fragment.app.FragmentActivity
import com.pietrantuono.live.application.AppComponent
import com.pietrantuono.live.contentlist.di.ContentListSubComponent
import com.pietrantuono.live.contentlist.model.ContentListModel
import com.pietrantuono.live.contentlist.model.RetrofitContentListModel
import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.viewmodel.ContentListIntent
import com.pietrantuono.live.contentlist.viewmodel.ContentListTransientEvent
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState
import com.pietrantuono.live.contentlist.viewmodel.ContentViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Component(modules = arrayOf(TestModule::class))
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance intentConsumer: IntentConsumer<ContentListIntent>,
            @BindsInstance viewStatesProducer: ViewStatesProducer<ContentListViewState>,
            @BindsInstance transientEventProducer: TransientEventProducer<ContentListTransientEvent>
        ): TestAppComponent
    }

    override val contentListSubComponentFactory: TestContentListSubComponent.TestFactory
}

@Subcomponent
interface TestContentListSubComponent : ContentListSubComponent {

    @Subcomponent.Factory
    interface TestFactory : ContentListSubComponent.Factory {
        override fun create(@BindsInstance appCompatActivity: FragmentActivity): TestContentListSubComponent
    }
}

@Module
class TestModule {

    @Provides
    fun ProvidesContentListClient(): ContentListClient = TODO()

}