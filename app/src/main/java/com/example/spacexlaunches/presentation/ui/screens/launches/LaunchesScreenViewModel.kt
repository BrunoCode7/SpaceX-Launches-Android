package com.example.spacexlaunches.presentation.ui.screens.launches

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.data.enums.Filter
import com.example.spacexlaunches.domain.useCases.GetAllLaunchesUseCase
import com.example.spacexlaunches.domain.useCases.GetSuccessfulLaunchesUseCase
import com.example.spacexlaunches.utils.LaunchesDataState
import com.example.spacexlaunches.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LaunchesScreenViewModel @Inject constructor(
    private val getAllLaunchesList:GetAllLaunchesUseCase,
    private val getSuccessfulLaunchesUseCase: GetSuccessfulLaunchesUseCase
    ):ViewModel(){

        private val _state = mutableStateOf(LaunchesDataState())
        val state : State<LaunchesDataState> = _state

    init {
        refreshData()
    }

    fun refreshData(filter:Filter = Filter.All){
        if (filter == Filter.All){
            getAllLaunches()
        }else{
            getSuccessfulLaunches()
        }
    }
    private fun getAllLaunches(){
        getAllLaunchesList().onEach {
            when (it){
                is Resource.Loading -> {
                    _state.value = LaunchesDataState(isLoading = true)
                }
                is Resource.Data -> {
                    _state.value = LaunchesDataState(data = it.data)
                }
                is Resource.Error -> {
                    _state.value = LaunchesDataState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSuccessfulLaunches(){
        getSuccessfulLaunchesUseCase().onEach {
            when (it){
                is Resource.Loading -> {
                    _state.value = LaunchesDataState(isLoading = true)
                }
                is Resource.Data -> {
                    _state.value = LaunchesDataState(data = it.data)
                }
                is Resource.Error -> {
                    _state.value = LaunchesDataState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}