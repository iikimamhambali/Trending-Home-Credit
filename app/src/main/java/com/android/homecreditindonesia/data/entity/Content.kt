package com.android.homecreditindonesia.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(@SerializedName("data") var data: List<ContentData>) : Parcelable