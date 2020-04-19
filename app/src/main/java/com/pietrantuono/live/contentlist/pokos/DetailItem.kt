package com.pietrantuono.live.contentlist.pokos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailItem(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("subtitle")
    @Expose
    var subtitle: String? = null,
    @SerializedName("body")
    @Expose
    var body: String? = null,
    @SerializedName("date")
    @Expose
    var date: String? = null
)