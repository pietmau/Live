package com.pietrantuono.live.contentlist.view.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.live.R
import com.pietrantuono.live.contentlist.pokos.ContentListItem

class ContentListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title by lazy { itemView.findViewById<TextView>(R.id.title) }
    private val subTitle by lazy { itemView.findViewById<TextView>(R.id.subtitle) }
    private val id by lazy { itemView.findViewById<TextView>(R.id.itemId) }
    private val date by lazy { itemView.findViewById<TextView>(R.id.date) }

    fun bind(item: ContentListItem, callback: (id: Int) -> Unit) {
        title.text = item.title
        subTitle.text = item.subtitle
        id.text = "${item.id}"
        date.text = item.date
        itemView.setOnClickListener {
            callback(item.id)
        }
    }

}