package com.android.homecreditindonesia.entity

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(ContentDeserializer::class)
abstract class ContentData(
    val sectionData: String
) : Parcelable