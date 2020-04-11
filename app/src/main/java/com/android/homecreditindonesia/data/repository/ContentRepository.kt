package com.android.homecreditindonesia.data.repository

import androidx.lifecycle.LiveData
import com.android.homecreditindonesia.data.entity.Content
import com.android.homecreditindonesia.data.response.ApiResponse
import com.android.homecreditindonesia.app.AppExecutors
import com.android.homecreditindonesia.data.response.SourceStatus
import com.android.homecreditindonesia.data.network.ContentService

class ContentRepository(
    private val appExecutors: AppExecutors,
    private val service: ContentService
) {

    fun getContent(): LiveData<SourceStatus<Content>> {
        return object : BaseRepositoryLiveData<Content>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<Content>> {
                return service.getContentHome()
            }
        }.asLiveData()
    }
}