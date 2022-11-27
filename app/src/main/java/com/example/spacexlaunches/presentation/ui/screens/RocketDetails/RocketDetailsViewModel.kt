package com.example.spacexlaunches.presentation.ui.screens.RocketDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.domain.useCases.GetRocketDetailsUseCase
import com.example.spacexlaunches.utils.Resource
import com.example.spacexlaunches.utils.RocketDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RocketDetailsViewModel @Inject constructor(
    private val getRocketDetailsUseCase: GetRocketDetailsUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _state = mutableStateOf(RocketDataState())
    val state : State<RocketDataState> = _state

    init {
        savedStateHandle.get<String>("rocketId")?.let { rocketId -> getRocketDetails(rocketId = rocketId) }
    }
    private fun getRocketDetails(rocketId:String){
        getRocketDetailsUseCase(rocketId = rocketId).onEach {
            when (it){
                is Resource.Loading -> {
                    _state.value = RocketDataState(isLoading = true)
                }
                is Resource.Data -> {
                    _state.value = RocketDataState(data = it.data)
                }
                is Resource.Error -> {
                    _state.value = RocketDataState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}