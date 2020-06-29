package com.android.catfacts.data.repository

import android.util.Log
import com.android.catfacts.core.network.Result
import com.android.catfacts.data.api.ServerFailure
import com.android.catfacts.data.model.CatFactItemResponse
import retrofit2.Response
import java.io.IOException

interface Repository {
    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>
    ): T {
        val response = call.invoke()
        if (response.isSuccessful) return response.body()!!

        Log.e("Repository", "Error Occurred during getting Api result")
        throw ServerFailure()
    }

    suspend fun getCatFacts(): Result<MutableList<String>>
}