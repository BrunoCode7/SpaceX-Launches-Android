package com.example.spacexlaunches.data.models.launches

data class Fairings(
    val recovered: Boolean?,
    val recovery_attempt: Boolean?,
    val reused: Boolean?,
    val ships: List<String?>?
)