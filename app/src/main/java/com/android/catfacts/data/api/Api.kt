package com.android.catfacts.data.api

import com.android.catfacts.data.dtos.CatFactDetailsResponse
import com.android.catfacts.data.dtos.CatFactResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/facts")
    suspend fun getCatFacts(): Response<CatFactResponse>

    @GET("/facts/{id}")
    suspend fun getFactDetails(@Path("id") id: String): Response<CatFactDetailsResponse>
}