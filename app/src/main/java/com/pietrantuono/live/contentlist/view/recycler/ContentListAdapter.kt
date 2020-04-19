package com.pietrantuono.live.contentlist.view.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pietrantuono.live.contentlist.pokos.ContentListItem

class ContentListAdapter : ListAdapter<ContentListItem, ContentListViewHolder>(ContentListDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentListViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ContentListViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

