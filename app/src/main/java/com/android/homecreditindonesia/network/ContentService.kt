package com.android.homecreditindonesia.network

import androidx.lifecycle.LiveData
import com.android.homecreditindonesia.entity.ContentData
import retrofit2.http.GET

interface ContentService {

    @GET("home")
    fun getContentHome(): LiveData<ContentData>
}