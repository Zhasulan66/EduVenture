package com.example.eduventure.data.repository

import com.example.eduventure.data.remote.EduVentureApiService
import com.example.eduventure.domain.repository.EduVentureRepository

class EduVentureRepositoryImpl(
    private val api: EduVentureApiService
) : EduVentureRepository {

    override suspend fun getAllUniversity() {
        api.getAllUniversity()
    }
}