package com.example.eduventure.di

import com.example.eduventure.data.remote.EduVentureApiService
import com.example.eduventure.data.repository.EduVentureRepositoryImpl
import com.example.eduventure.domain.repository.EduVentureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EduVentureModule {

    @Provides
    @Singleton
    fun provideEduVentureApiService(): EduVentureApiService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://blogkz-5e22e29f32e9.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(EduVentureApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideEduVentureRepository(
        eduVentureApiService: EduVentureApiService
    ) : EduVentureRepository {
        return EduVentureRepositoryImpl(eduVentureApiService)
    }
}