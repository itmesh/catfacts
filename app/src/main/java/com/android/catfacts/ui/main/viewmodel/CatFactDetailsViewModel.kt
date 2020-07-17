package com.android.catfacts.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.android.catfacts.core.network.Result
import com.android.catfacts.data.repository.Repository
import com.android.catfacts.ui.main.viewmodel.model.CatFact

class CatFactDetailsViewModel(private val repository: Repository) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default


    private val scope = CoroutineScope(coroutineContext)

    val catFactDetailsData = MutableLiveData<CatFact>()
    val errorLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun fetchCatFactDetails(id: String) {
        loadingLiveData.postValue(true)
        scope.launch {
            when (val result = repository.getCatFactDetails(id)) {
                is Result.Success -> {
                    catFactDetailsData.postValue(CatFact(result.data.text, result.data.updatedAt))
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