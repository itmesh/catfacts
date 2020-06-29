package com.android.catfacts.data.api

abstract class Failure : Exception()

class ServerFailure : Failure()