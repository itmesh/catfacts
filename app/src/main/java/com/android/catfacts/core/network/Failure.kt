package com.android.catfacts.core.network

abstract class Failure : Exception()

class ServerFailure : Failure()