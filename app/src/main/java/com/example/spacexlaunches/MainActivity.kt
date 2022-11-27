package com.example.spacexlaunches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.example.spacexlaunches.presentation.ui.theme.SpaceXLaunchesTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.spacexlaunches.presentation.ui.screens.RocketDetails.RocketDetailsScreen
import com.example.spacexlaunches.presentation.ui.screens.launches.LaunchesDataListScreen
import com.example.spacexlaunches.utils.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXLaunchesTheme {
                Surface() {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.LaunchesScreen.route){
                        composable(route = Screen.LaunchesScreen.route){
                            LaunchesDataListScreen(navController)
                        }
                        composable(route = Screen.RocketDetailsScreen.route +"/{rocketId}", arguments = listOf(navArgument("rocketId") { type = NavType.StringType })){
                            entry ->
                            entry.arguments?.getString("rocketId")
                                ?.let { RocketDetailsScreen(rocketId = it, navController = navController) }
                        }
                    }
                }
            }
        }
    }
}