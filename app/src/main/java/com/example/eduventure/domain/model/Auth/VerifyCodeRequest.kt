package com.example.eduventure.domain.model.Auth

data class VerifyCodeRequest(
    val code: String,
    val email: String
)
