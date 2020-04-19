package com.pietrantuono.live.contentlist.pokos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentList( //TODO use interceptor instead.
    @SerializedName("items")
    @Expose
    var contentListItems: List<ContentListItem>? = null
)