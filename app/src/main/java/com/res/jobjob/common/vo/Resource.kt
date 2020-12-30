package com.res.jobjob.common.vo

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorMsg: String) : Resource<T>()
}
