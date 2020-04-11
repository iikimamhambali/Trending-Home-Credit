package com.android.homecreditindonesia.data.entity

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(ContentDeserializer::class)
abstract class ContentData(
    val sectionData: String
) : Parcelable