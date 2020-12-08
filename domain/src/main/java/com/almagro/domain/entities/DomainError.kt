package com.almagro.domain.entities

sealed class DomainError {
    object ServerError : DomainError()
    class SpecificError(val statusMessage: String, val statusCode: String) : DomainError()
}