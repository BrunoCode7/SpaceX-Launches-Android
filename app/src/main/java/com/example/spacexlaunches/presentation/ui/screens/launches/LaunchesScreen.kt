package com.example.spacexlaunches.presentation.ui.screens.launches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.spacexlaunches.presentation.ui.screens.launches.components.launchesListItem
import com.example.spacexlaunches.utils.Screen
import androidx.compose.runtime.*
import com.example.spacexlaunches.data.enums.Filter
import com.example.spacexlaunches.presentation.ui.screens.launches.components.filterDialog

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchesDataListScreen(
    navController: NavController,
    viewModel: LaunchesScreenViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    var showDialog by remember {
        mutableStateOf(false)
    }
    var selectedFilterValue by remember {
        mutableStateOf(Filter.All)
    }

    val ptrState=
        rememberPullRefreshState(state.isLoading, {viewModel.refreshData(selectedFilterValue)})
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(com.example.spacexlaunches.R.string.app_name)) },
            actions = {
                IconButton(onClick = {
                    // open filter check
                    showDialog = true
                }) {
                    Icon(painterResource(id = com.example.spacexlaunches.R.drawable.ic_baseline_filter_alt_24), contentDescription = "Icon")
                }
            }
        )
    }
    ) { _ ->
        Box(modifier = Modifier
            .fillMaxSize()
            .pullRefresh(ptrState)) {
            LazyColumn(state = rememberLazyListState()) {
                if (!state.isLoading) {
                    items(state.data) { launchItem ->
                        launchesListItem(launchItem = launchItem, onItemClick = {
                            navController.navigate(Screen.RocketDetailsScreen.route + "/${launchItem.rocket}")
                        })
                    }
                }
            }
            PullRefreshIndicator(state.isLoading, ptrState, Modifier.align(Alignment.TopCenter))
            if (state.error.isNotEmpty()){
                Text(text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .align(Alignment.Center)
                )
            }
        }
        val options = listOf(Filter.All,Filter.Successful)
        filterDialog(showDialog = showDialog, optionsList = options , selected = selectedFilterValue , setSelected ={
            selectedFilterValue = it
            viewModel.refreshData(selectedFilterValue)
        }, onDismissRequested = {
            showDialog = false
        } )
    }

}