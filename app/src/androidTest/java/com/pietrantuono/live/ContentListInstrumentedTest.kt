package com.pietrantuono.live

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pietrantuono.live.contentlist.pokos.ContentListItem
import com.pietrantuono.live.contentlist.viewmodel.ContentListIntent
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Content
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Error
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Loading
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val ID = 123
private const val TITLE = "title"
private const val SUBTITLE = "subtitle"
private const val DATE = "date"
private const val MESSAGE = "message"

@RunWith(AndroidJUnit4::class)
class ContentListInstrumentedTest {
    @get:Rule
    var activityRule = ContentListActivityRule()
    private val content = Content(listOf(ContentListItem(id = ID, title = TITLE, subtitle = SUBTITLE, date = DATE)))
    private val openDetail = slot<ContentListIntent.OpenDetail>()

    @Test
    fun when_loading_then_shows_loading() {
        // When
        activityRule.viewStates.postValue(Loading)

        // Then
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun when_content_then_shows_content() {
        // When
        activityRule.viewStates.postValue(content)

        // Then
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
        onView(withText(ID.toString())).check(matches(isDisplayed()));
        onView(withText(TITLE)).check(matches(isDisplayed()));
        onView(withText(SUBTITLE)).check(matches(isDisplayed()));
        onView(withText(DATE)).check(matches(isDisplayed()));
    }

    @Test
    fun when_error_then_shows_error() {
        // When
        activityRule.viewStates.postValue(Error(MESSAGE))

        // Then
        onView(withText(MESSAGE)).check(matches(isDisplayed()))
    }

    @Test
    fun when_clicks_then_command_is_sent() {
        // When
        activityRule.viewStates.postValue(content)

        // Then
        onView(withId(R.id.card)).perform(click())
        verify { activityRule.intentConsumer.acceptIntent(capture(openDetail)) }
        assertThat(openDetail.captured.id).isEqualTo(ID)
    }
}
