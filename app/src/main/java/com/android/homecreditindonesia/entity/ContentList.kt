package com.android.homecreditindonesia.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentList(
    @SerializedName("article_title") var articleTitle: String = "",
    @SerializedName("article_image") var articleImage: String = "",
    @SerializedName("link") var link: String = "",
    @SerializedName("product_name") var productName: String = "",
    @SerializedName("product_image") var productImage: String = ""
) : Parcelable