package com.android.homecreditindonesia.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentArticle(
    @SerializedName("section") val sectionArticle: String,
    @SerializedName("section_title") val sectionTitle: String,
    @SerializedName("items") val items: List<ContentItemArticle>
) : ContentData(sectionArticle)