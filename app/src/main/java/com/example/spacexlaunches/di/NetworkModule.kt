package com.example.spacexlaunches.di

import com.example.spacexlaunches.data.remote.SpaceXApi
import com.example.spacexlaunches.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val READ_TIMEOUT = 40
    private val WRITE_TIMEOUT = 40
    private val CONNECTION_TIMEOUT = 10

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
        fun provideSpaceXApi(retrofit: Retrofit): SpaceXApi {
        return retrofit.create(SpaceXApi::class.java)
    }
}