package com.example.spacexlaunches.data.remote

import com.example.spacexlaunches.data.models.launches.LaunchItem
import com.example.spacexlaunches.data.models.rockets.Rocket
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {

    @GET("v5/launches")
    suspend fun getLaunches() : List<LaunchItem>

    @GET("v4/rockets/{rocketId}")
    suspend fun getRocketDetailsByRocketId(@Path ("rocketId") rocketId:String) : Rocket

}