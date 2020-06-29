package com.android.catfacts.data.api

import com.android.catfacts.data.model.CatFactDetailsResponse
import com.android.catfacts.data.model.CatFactResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/facts")
    suspend fun getCatFacts(): Response<CatFactResponse>

    @GET("/facts/{id}")
    fun getFactDetails(@Path("id") id: String): List<CatFactDetailsResponse>
}