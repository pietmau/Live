package com.pietrantuono.live

import com.pietrantuono.live.application.AppComponent
import com.pietrantuono.live.contentlist.viewmodel.ContentListIntent
import com.pietrantuono.live.contentlist.viewmodel.ContentListTransientEvent
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState
import dagger.BindsInstance
import dagger.Component

@Component()
internal interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance intentConsumer: IntentConsumer<ContentListIntent>,
            @BindsInstance viewStatesProducer: ViewStatesProducer<ContentListViewState>,
            @BindsInstance transientEventProducer: TransientEventProducer<ContentListTransientEvent>
        ): TestAppComponent
    }
}
