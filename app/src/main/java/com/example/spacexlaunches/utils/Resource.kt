package com.example.spacexlaunches.utils

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Error<T>(var message: String) : Resource<T>()
    data class Data<T>(var data: T) : Resource<T>()
}