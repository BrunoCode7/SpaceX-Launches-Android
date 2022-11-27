package com.example.spacexlaunches.data.repositories.launches

import com.example.spacexlaunches.data.models.launches.LaunchItem
import com.example.spacexlaunches.data.remote.SpaceXApi
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor( private val api:SpaceXApi):LaunchesRepository{
    override suspend fun getLaunches(): List<LaunchItem> {
        return api.getLaunches()
    }
}