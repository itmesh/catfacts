package com.android.catfacts.data.repository

import com.android.catfacts.core.network.Result
import com.android.catfacts.data.api.Api
import com.android.catfacts.core.network.ServerFailure

class RepositoryImp(private val api: Api) : Repository {
    override suspend fun getCatFacts(): Result<MutableList<String>> {
        return try {
            val response = api.getCatFacts()
            if (response.isSuccessful) {
                val data = response.body()!!
                return Result.Success(data.all.map { it._id }.toMutableList());
            }
            return Result.Error("Response not came")
        } catch (e: ServerFailure) {
            Result.Error("Server is unavailable")
        }
    }
}