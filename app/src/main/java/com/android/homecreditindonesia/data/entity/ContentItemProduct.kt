package com.android.homecreditindonesia.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentItemProduct(
    @SerializedName("product_name") var productName: String = "",
    @SerializedName("product_image") var productImage: String = "",
    @SerializedName("link") var link: String = ""
) : Parcelable