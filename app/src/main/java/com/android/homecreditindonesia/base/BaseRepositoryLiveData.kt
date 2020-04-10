package com.android.homecreditindonesia.base

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.homecreditindonesia.helper.*
import java.io.IOException

abstract class BaseRepositoryLiveData<Type>(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<SourceStatus<Type>>()

    init {
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: SourceStatus<Type>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = loadFromNetwork()
        setValue(SourceStatus.loading())
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        val newResponse = processResponse(response)
                        appExecutors.mainThread().execute {
                            result.addSource(apiResponse) {
                                setValue(SourceStatus.success(newResponse))
                            }
                        }
                    }
                }
                is ApiErrorResponse -> {
                    appExecutors.mainThread().execute {
                        when {
                            response.error is IOException -> setValue(
                                SourceStatus.networkFailed(
                                    response.error
                                )
                            )
                            else -> setValue(SourceStatus.error(response.error))
                        }
                    }
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<SourceStatus<Type>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<Type>) = response.body

    @MainThread
    protected abstract fun loadFromNetwork(): LiveData<ApiResponse<Type>>
}