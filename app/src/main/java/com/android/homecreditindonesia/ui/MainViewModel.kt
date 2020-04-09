package com.android.homecreditindonesia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.homecreditindonesia.base.BaseViewModel
import com.android.homecreditindonesia.entity.Content
import com.android.homecreditindonesia.entity.ContentData
import com.android.homecreditindonesia.helper.SourceStatus
import com.android.homecreditindonesia.repository.ContentRepository

class MainViewModel(private val repository: ContentRepository) : BaseViewModel() {

    private val contentRequest = MutableLiveData<Boolean>()

    val contentData: LiveData<SourceStatus<Content>> = Transformations
        .switchMap(contentRequest) {
            repository.getContent()
        }

    fun getContent() {
        contentRequest.value = true
    }
}