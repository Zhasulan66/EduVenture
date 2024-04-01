package com.example.eduventure.domain.model.Auth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("auth_token")
    var authToken: String
)
