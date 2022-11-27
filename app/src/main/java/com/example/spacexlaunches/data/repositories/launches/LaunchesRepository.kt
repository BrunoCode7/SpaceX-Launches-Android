package com.example.spacexlaunches.data.repositories.launches

import com.example.spacexlaunches.data.models.launches.LaunchItem

interface LaunchesRepository {
    suspend fun getLaunches():List<LaunchItem>
}