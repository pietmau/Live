package com.pietrantuono.live.contentlist.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pietrantuono.live.R
import com.pietrantuono.live.contentlist.pokos.ContentListItem

class ContentListAdapter : ListAdapter<ContentListItem, ContentListViewHolder>(ContentListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun onBindViewHolder(holder: ContentListViewHolder, position: Int) =
        holder.bind(getItem(position))
}

