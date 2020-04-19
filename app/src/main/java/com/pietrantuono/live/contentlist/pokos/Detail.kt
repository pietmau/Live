package com.pietrantuono.live.contentlist.pokos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("item")
    @Expose
    var detailItem: DetailItem? = null
)