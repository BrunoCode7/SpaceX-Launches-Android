package com.example.spacexlaunches.domain.useCases

import com.example.spacexlaunches.data.models.rockets.Rocket
import com.example.spacexlaunches.data.repositories.rockets.RocketsRepository
import com.example.spacexlaunches.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRocketDetailsUseCase @Inject constructor(private val repository: RocketsRepository){

    operator fun invoke(rocketId:String):Flow<Resource<Rocket>> = flow {
        try {
            emit(Resource.Loading)
            val data = repository.getRocketDetailsById(rocketId)
            emit(Resource.Data(data))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?:"Something went wrong!"))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server!"))
        }
    }
}