package com.android.catfacts.core.network

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val failure: Failure) : Result<Nothing>()
}