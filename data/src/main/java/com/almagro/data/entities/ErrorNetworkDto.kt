package com.almagro.data.entities

import com.google.gson.annotations.SerializedName

data class ErrorNetworkDto(
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("status_code")
    val statusCode: String
)