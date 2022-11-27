package com.example.spacexlaunches.di

import com.example.spacexlaunches.data.remote.SpaceXApi
import com.example.spacexlaunches.data.repositories.launches.LaunchesRepository
import com.example.spacexlaunches.data.repositories.launches.LaunchesRepositoryImpl
import com.example.spacexlaunches.data.repositories.rockets.RocketsRepository
import com.example.spacexlaunches.data.repositories.rockets.RocketsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideLaunchesRepository (spaceXApi: SpaceXApi):LaunchesRepository{
        return LaunchesRepositoryImpl(spaceXApi)
    }

    @Provides
    @Singleton
    fun provideRocketsRepository (spaceXApi: SpaceXApi):RocketsRepository{
        return RocketsRepositoryImpl(spaceXApi)
    }
}