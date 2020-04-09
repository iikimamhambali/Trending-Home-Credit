package com.android.homecreditindonesia.repository

import androidx.lifecycle.LiveData
import com.android.homecreditindonesia.base.BaseRepositoryLiveData
import com.android.homecreditindonesia.entity.Content
import com.android.homecreditindonesia.helper.AbsentLiveData
import com.android.homecreditindonesia.helper.ApiResponse
import com.android.homecreditindonesia.helper.AppExecutors
import com.android.homecreditindonesia.helper.SourceStatus
import com.android.homecreditindonesia.network.ContentService

class ContentRepository(
    private val appExecutors: AppExecutors,
    private val service: ContentService
) {

    fun getContent(): LiveData<SourceStatus<Content>> {
        return object : BaseRepositoryLiveData<Content>(appExecutors) {
            override fun saveFromNetwork(item: Content) {

            }

            override fun shouldFetchFromNetwork(data: Content?): Boolean {
                return true
            }

            override fun loadFromLocal(): LiveData<Content> {
                return AbsentLiveData.create()
            }

            override fun loadFromNetwork(): LiveData<ApiResponse<Content>> {
                return service.getContentHome()
            }

        }.asLiveData()
    }
}