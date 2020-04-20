package com.pietrantuono.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.pietrantuono.live.application.LiveApp
import com.pietrantuono.live.contentlist.view.ContentListActivity
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState
import io.mockk.mockk

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ContentListInstrumentedTest {
    private val states: MutableLiveData<ContentListViewState> = MutableLiveData()
    private val viewStatesProducer: ViewStatesProducer<ContentListViewState> = object : ViewStatesProducer<ContentListViewState> {
        override val viewStates: LiveData<ContentListViewState> = states
    }

    @get:Rule
    var activityRule: ActivityTestRule<ContentListActivity> = object : ActivityTestRule<ContentListActivity>(ContentListActivity::class.java) {
        override fun beforeActivityLaunched() {
            val application = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as LiveApp
            application.appComponent = DaggerTestAppComponent.factory().create(mockk(),mockk(),mockk())
        }
    }

    @Test
    fun name() {
        //fail()
    }
}
