package com.almagro.data.entities

import com.almagro.domain.entities.DomainError
import com.google.gson.annotations.SerializedName

sealed class Failure : Throwable() {

    object ServerError : Failure()
    object Timeout : Failure()
    object NoData : Failure()

    class ErrorNetworkDto(
        @SerializedName("status_message")
        val statusMessage: String,
        @SerializedName("status_code")
        val statusCode: String
    ) : Failure()
}

fun Failure.parseError() =
    when(this) {
        is Failure.ErrorNetworkDto -> DomainError.SpecificError(this.statusMessage, this.statusCode)
        else -> DomainError.ServerError
    }