package com.android.homecreditindonesia.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentProduct(
    @SerializedName("section") val sectionProduct: String,
    @SerializedName("items") val items: List<ContentItemProduct>
) : ContentData(sectionProduct)