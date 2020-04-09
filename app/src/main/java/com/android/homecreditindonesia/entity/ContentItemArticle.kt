package com.android.homecreditindonesia.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentItemArticle(
    @SerializedName("article_title") var articleTitle: String = "",
    @SerializedName("article_image") var articleImage: String = "",
    @SerializedName("link") var link: String = ""
) : Parcelable