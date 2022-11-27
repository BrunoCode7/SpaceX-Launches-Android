package com.example.spacexlaunches.domain.useCases

import com.example.spacexlaunches.data.models.launches.LaunchItem
import com.example.spacexlaunches.data.repositories.launches.LaunchesRepository
import com.example.spacexlaunches.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllLaunchesUseCase @Inject constructor(private val repository: LaunchesRepository){

     operator fun invoke():Flow<Resource<List<LaunchItem>>> = flow {
        try {
            emit(Resource.Loading)
            val data = repository.getLaunches()
            emit(Resource.Data(data))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?:"Something went wrong!"))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server!"))
        }

    }
}