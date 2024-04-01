package com.example.eduventure.domain.model.Auth

data class ConfirmCodeRequest(
    val email: String,
    val code: String,
)
