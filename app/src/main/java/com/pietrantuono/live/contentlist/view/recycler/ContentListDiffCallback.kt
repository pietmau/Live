package com.pietrantuono.live.contentlist.view.recycler

import androidx.recyclerview.widget.DiffUtil
import com.pietrantuono.live.contentlist.pokos.ContentListItem

object ContentListDiffCallback : DiffUtil.ItemCallback<ContentListItem>() {

    override fun areItemsTheSame(oldItem: ContentListItem, newItem: ContentListItem) = oldItem.id === newItem.id

    override fun areContentsTheSame(oldItem: ContentListItem, newItem: ContentListItem) = oldItem == newItem
}