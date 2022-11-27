package com.example.spacexlaunches.data.repositories.rockets

import com.example.spacexlaunches.data.models.rockets.Rocket
import com.example.spacexlaunches.data.remote.SpaceXApi
import javax.inject.Inject

class RocketsRepositoryImpl @Inject constructor(private val api:SpaceXApi):RocketsRepository {
    override suspend fun getRocketDetailsById(id: String):Rocket {
        return api.getRocketDetailsByRocketId(id)
    }
}