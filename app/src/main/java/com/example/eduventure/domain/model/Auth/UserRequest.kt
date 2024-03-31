package com.example.eduventure.domain.model.Auth

data class UserRequest(
    val username: String,
    val email: String,
    val password: String,
)
