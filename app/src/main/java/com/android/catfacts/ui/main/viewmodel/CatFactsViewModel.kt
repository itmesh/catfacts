package com.android.catfacts.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.android.catfacts.core.network.Result
import com.android.catfacts.data.repository.Repository

class CatFactsViewModel(private val repository: Repository) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default


    private val scope = CoroutineScope(coroutineContext)

    val catFactsLiveData = MutableLiveData<MutableList<String>>()
    val errorLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun fetchCatFacts() {
        loadingLiveData.postValue(true)
        scope.launch {
            when (val result = repository.getCatFacts()) {
                is Result.Success -> {
                    catFactsLiveData.postValue(result.data)
                }
                is Result.Error -> {
                    errorLiveData.postValue(result.failure.failureMessage)
                }
            }

            loadingLiveData.postValue(false)
        }
    }

    fun cancelAllRequest() = coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        if (coroutineContext.isActive) cancelAllRequest()
    }
}