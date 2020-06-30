package com.android.catfacts.data.repository

import com.android.catfacts.core.network.Result

interface Repository {
    suspend fun getCatFacts(): Result<MutableList<String>>
}