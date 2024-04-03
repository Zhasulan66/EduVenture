package com.example.eduventure.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val email: String,
    val username: String,
    @SerializedName("phone_num")
    val phoneNum: String?,
    val photo: String?
)
