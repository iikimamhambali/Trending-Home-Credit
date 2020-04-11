package com.android.homecreditindonesia.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.homecreditindonesia.base.BaseViewModel
import com.android.homecreditindonesia.data.entity.Content
import com.android.homecreditindonesia.data.response.SourceStatus
import com.android.homecreditindonesia.data.repository.ContentRepository

class ContentViewModel(private val repository: ContentRepository) : BaseViewModel() {

    private val contentRequest = MutableLiveData<Boolean>()

    val contentData: LiveData<SourceStatus<Content>> = Transformations
        .switchMap(contentRequest) {
            repository.getContent()
        }

    fun getContent() {
        contentRequest.value = true
    }
}