package com.example.spacexlaunches.data.repositories.rockets

import com.example.spacexlaunches.data.models.rockets.Rocket

interface RocketsRepository {
    suspend fun getRocketDetailsById(id:String):Rocket
}