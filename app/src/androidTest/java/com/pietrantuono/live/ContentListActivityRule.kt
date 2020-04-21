package com.pietrantuono.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pietrantuono.live.application.LiveApp
import com.pietrantuono.live.contentlist.view.ContentListActivity
import com.pietrantuono.live.contentlist.viewmodel.ContentListIntent
import com.pietrantuono.live.contentlist.viewmodel.ContentListTransientEvent
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState
import io.mockk.mockk

class ContentListActivityRule : ActivityTestRule<ContentListActivity>(ContentListActivity::class.java) {
    internal val transientEvents: LiveData<ContentListTransientEvent> = MutableLiveData()
    private val transientEventProducer: TransientEventProducer<ContentListTransientEvent> = object : TransientEventProducer<ContentListTransientEvent> {
        override val transientEvents: LiveData<ContentListTransientEvent> = this@ContentListActivityRule.transientEvents
    }
    internal val viewStates: MutableLiveData<ContentListViewState> = MutableLiveData()
    private val viewStatesProducer: ViewStatesProducer<ContentListViewState> = object : ViewStatesProducer<ContentListViewState> {
        override val viewStates: LiveData<ContentListViewState> = this@ContentListActivityRule.viewStates
    }
    internal val intentConsumer: IntentConsumer<ContentListIntent> = mockk(relaxed = true)

    override fun beforeActivityLaunched() {
        val application = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as LiveApp
        val component =
            DaggerTestAppComponent.factory().create(
                intentConsumer = intentConsumer,
                viewStatesProducer = viewStatesProducer,
                transientEventProducer = transientEventProducer
            )
        application.appComponent = component
    }
}