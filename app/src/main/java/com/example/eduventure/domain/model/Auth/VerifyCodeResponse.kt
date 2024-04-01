package com.example.eduventure.domain.model.Auth

data class VerifyCodeResponse(
    val message: String?,
    val email: String?,
    val detail: String?
)
