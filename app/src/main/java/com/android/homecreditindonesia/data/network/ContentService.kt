package com.android.homecreditindonesia.data.network

import androidx.lifecycle.LiveData
import com.android.homecreditindonesia.data.entity.Content
import com.android.homecreditindonesia.data.response.ApiResponse
import retrofit2.http.GET

interface ContentService {

    @GET("home")
    fun getContentHome(): LiveData<ApiResponse<Content>>
}