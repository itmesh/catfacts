package com.android.catfacts.core.network

abstract class Failure(val failureMessage: String)

class ServerFailure : Failure("Some problem with server, try again later...")

class ExceptionRepositoryFailure : Failure("Unknown error, try again...")

class NoInternetFailure : Failure("Check internet connection and try again...")
