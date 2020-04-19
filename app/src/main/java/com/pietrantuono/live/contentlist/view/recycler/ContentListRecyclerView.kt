package com.pietrantuono.live.contentlist.view.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.live.contentlist.pokos.ContentListItem

class ContentListRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RecyclerView(context, attrs, defStyleAttr) {
    private val contentListAdapter: ContentListAdapter
        get() = adapter as ContentListAdapter

    var callback: (id: Int) -> Unit = {}
        set(value) {
            contentListAdapter.callback = value
        }

    init {
        layoutManager = LinearLayoutManager(context)
        adapter = ContentListAdapter()
    }

    fun submitList(items: List<ContentListItem>) = contentListAdapter.submitList(items)
}