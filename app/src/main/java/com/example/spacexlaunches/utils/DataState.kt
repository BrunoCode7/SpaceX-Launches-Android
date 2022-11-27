package com.example.spacexlaunches.utils

import com.example.spacexlaunches.data.models.launches.LaunchItem
import com.example.spacexlaunches.data.models.rockets.Rocket

data class LaunchesDataState (
val isLoading:Boolean = false,
val data:List<LaunchItem> = emptyList(),
val error:String = ""
        )

data class RocketDataState (
    val isLoading:Boolean = false,
    val data:Rocket? = null,
    val error:String = ""
)