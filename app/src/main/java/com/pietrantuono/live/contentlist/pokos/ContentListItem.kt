package com.pietrantuono.live.contentlist.pokos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentListItem(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("subtitle")
    @Expose
    var subtitle: String? = null,
    @SerializedName("date")
    @Expose
    var date: String? = null
) : Parcelable