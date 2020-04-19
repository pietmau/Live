package com.pietrantuono.live.contentlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.pietrantuono.live.contentlist.model.ContentListModel
import com.pietrantuono.live.contentlist.pokos.ContentList
import com.pietrantuono.live.contentlist.pokos.ContentListItem
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val TITLE = "title"
private const val ANOTHER_TITLE = "another_title"
private const val ID = 123

class ContentViewModelTest {
    private lateinit var viewModel: ContentViewModel
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewStateobserver = mockk<Observer<ContentListViewState>>(relaxed = true)
    private val transientEventsObserver = mockk<Observer<ContentListTransientEvent>>(relaxed = true)

    @Before
    fun setUp() {
        viewModel = ContentViewModel(mockk(relaxed = true) {
            every { contentList } returns flowOf(ContentList(contentListItems = getContents()))
        }, Dispatchers.Unconfined)
        viewModel.viewStates.observeForever(viewStateobserver)
        viewModel.transientEvents.observeForever(transientEventsObserver)
    }

    @Test
    fun `when starts emits loading`() {
        // Given
        val viewModel = ContentViewModel(mockk(relaxed = true), Dispatchers.Unconfined)

        // When
        viewModel.viewStates.observeForever(viewStateobserver)

        // Then
        verify { viewStateobserver.onChanged(ofType<ContentListViewState.Loading>()) }
    }

    @Test
    fun `when starts queries model`() {
        // Given
        val model = mockk<ContentListModel>(relaxed = true)

        // When
        ContentViewModel(model, Dispatchers.Unconfined)

        // Then
        verify { model.contentList }
        confirmVerified(model)
    }

    @Test
    fun `when data are available emits data`() {
        verify { viewStateobserver.onChanged(ofType<ContentListViewState.Content>()) }
    }

    @Test
    fun `when emits data are sorted`() {
        // When
        val slot = slot<ContentListViewState.Content>()

        // Then
        verify { viewStateobserver.onChanged(capture(slot)) }
        assertThat(slot.captured.listContentListItems[0].title).isEqualTo(ANOTHER_TITLE)
        assertThat(slot.captured.listContentListItems[1].title).isEqualTo(TITLE)
    }

    @Test
    fun `when error then emits error`() {
        // Given
        val viewModel = ContentViewModel(mockk(relaxed = true) {
            every { contentList } returns flow { throw Exception() }
        }, Dispatchers.Unconfined)

        // When
        viewModel.viewStates.observeForever(viewStateobserver)

        // Then
        verify { viewStateobserver.onChanged(ofType<ContentListViewState.Error>()) }
    }

    @Test
    fun `when receives intent then emits open  `() {
        // When
        viewModel.acceptIntent(ContentListIntent.OpenDetail(ID))

        // Then
        verify { transientEventsObserver.onChanged(ofType<ContentListTransientEvent.OpenDetail>()) }
    }

    private fun getContents() = listOf(ContentListItem(id = ID, title = TITLE), ContentListItem(id = ID, title = ANOTHER_TITLE))
}