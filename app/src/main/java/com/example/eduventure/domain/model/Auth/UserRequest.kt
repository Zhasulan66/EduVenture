package com.example.eduventure.domain.model.Auth

data class UserRequest(
    val username: String,
    val email: String,
    //@SerializedName("phone_num")
    val phone_num: String,
    val password: String,
)
