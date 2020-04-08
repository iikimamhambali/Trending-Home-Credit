package com.android.homecreditindonesia.repository

import androidx.lifecycle.LiveData
import com.android.homecreditindonesia.base.BaseRepositoryLiveData
import com.android.homecreditindonesia.entity.ContentData
import com.android.homecreditindonesia.helper.AbsentLiveData
import com.android.homecreditindonesia.helper.ApiResponse
import com.android.homecreditindonesia.helper.AppExecutors
import com.android.homecreditindonesia.helper.SourceStatus
import com.android.homecreditindonesia.network.ContentService

class ContentRepository(
    private val appExecutors: AppExecutors,
    private val service: ContentService
) {

    fun getContent(): LiveData<SourceStatus<ContentData>> {
        return object : BaseRepositoryLiveData<ContentData>(appExecutors) {
            override fun saveFromNetwork(item: ContentData) {

            }

            override fun shouldFetchFromNetwork(data: ContentData?): Boolean {
                return true
            }

            override fun loadFromLocal(): LiveData<ContentData> {
                return AbsentLiveData.create()
            }

            override fun loadFromNetwork(): LiveData<ApiResponse<ContentData>> {
                return service.getContentHome()
            }

        }.asLiveData()
    }
}