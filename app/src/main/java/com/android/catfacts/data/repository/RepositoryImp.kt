package com.android.catfacts.data.repository

import com.android.catfacts.core.network.ExceptionRepositoryFailure
import com.android.catfacts.core.network.NoInternetFailure
import com.android.catfacts.core.network.Result
import com.android.catfacts.core.network.Result.Success
import com.android.catfacts.data.api.Api
import com.android.catfacts.core.network.ServerFailure
import com.android.catfacts.data.dtos.CatFactDetailsResponse
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RepositoryImp(private val api: Api) : Repository {

    private suspend fun <T : Any> call(apiCall: suspend () -> Response<T>): Result<T> = try {
        val response = apiCall()
        if (response.isSuccessful) {
            Success(response.body()!!)
        } else {
            Result.Error(ServerFailure())
        }
    } catch (e: SocketTimeoutException) {
        Result.Error(NoInternetFailure())
    } catch (e: UnknownHostException) {
        Result.Error(NoInternetFailure())
    } catch (e: Exception) {
        Result.Error(ExceptionRepositoryFailure())
    }

    override suspend fun getCatFacts(): Result<MutableList<String>> =
        when (val result = call { api.getCatFacts() }) {
            is Success -> {
                val data = result.data
                Success(data.all.map { it._id }.toMutableList());
            }
            is Result.Error -> result
        }

    override suspend fun getCatFactDetails(id: String): Result<CatFactDetailsResponse> =
        when (val result = call { api.getFactDetails(id) }) {
            is Success -> {
                val data = result.data
                Success(CatFactDetailsResponse(text = data.text, updatedAt = data.updatedAt))
            }
            is Result.Error -> result
        }

}