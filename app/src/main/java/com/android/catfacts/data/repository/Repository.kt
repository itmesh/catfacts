package com.android.catfacts.data.repository

import com.android.catfacts.core.network.Result
import com.android.catfacts.data.dtos.CatFactDetailsResponse

interface Repository {
    suspend fun getCatFacts(): Result<MutableList<String>>
    suspend fun getCatFactDetails(id: String) : Result<CatFactDetailsResponse>
}