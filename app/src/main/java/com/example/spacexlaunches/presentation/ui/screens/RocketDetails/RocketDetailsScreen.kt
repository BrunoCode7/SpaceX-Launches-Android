package com.example.spacexlaunches.presentation.ui.screens.RocketDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun RocketDetailsScreen(rocketId:String,
                        viewModel:RocketDetailsViewModel = hiltViewModel(),
                        navController: NavController){
    val state = viewModel.state.value

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(com.example.spacexlaunches.R.string.rocket_details_title)) },
            navigationIcon = {IconButton(onClick = {navController.popBackStack() }) {Icon(Icons.Filled.ArrowBack, "")                     } },        )
    }
    ) { _ ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(15.dp)){

            if (!state.isLoading) {
                state.data?.description?.let { Text(text = it, modifier = Modifier.align(Alignment.Center)) }
            }

            if (state.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}