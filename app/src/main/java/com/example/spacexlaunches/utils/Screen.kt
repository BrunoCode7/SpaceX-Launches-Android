package com.example.spacexlaunches.utils

sealed class Screen (val route:String) {
    object LaunchesScreen : Screen("launches-screen")
    object RocketDetailsScreen : Screen("rocket-details-screen")
}