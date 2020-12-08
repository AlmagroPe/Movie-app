package com.almagro.movieapp

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.almagro.data.entities.ErrorNetworkDto
import com.almagro.data.entities.Failure
import okhttp3.ResponseBody
import retrofit2.Response

suspend fun <Domain : Any> Response<Domain>?.unWrap(): Either<Failure, Domain> =
    try {
        this?.let {
            val code = it.code()
            val body: Domain? = it.body()

            return when {
                code in 200..208 && body != null -> getSuccessResult(body)
                code in 200..208 && body == null -> getNoResponseResult()
//                code in 400..512 && it.errorBody() != null -> getNoResponseResult(it.errorBody())
                code in 400..512 -> getFailureError()
                else -> getFailureError()
            }
        } ?: getFailureError()
    } catch (e: Throwable) {
        getFailureError()
    }

suspend fun <Domain : Any> Response<List<Domain>>?.unWrapList(): Either<Failure, List<Domain>> =
    try {
        this?.let {
            val code = it.code()
            val body: List<Domain>? = it.body()

            return when {
                code in 200..208 && body != null -> getSuccessResultList(body)
                code in 200..208 && body == null -> getNoResponseResult()
//                code in 200..208 && it.errorBody() != null -> getNoResponseResult(it.errorBody())
                code in 400..512 -> getFailureError()
                else -> getFailureError()
            }
        } ?: getFailureError()
    } catch (e: Throwable) {
        getFailureError()
    }

private fun <T : Any> getSuccessResult(body: T): Either<Failure, T> =
    body.right()

private fun <T : Any> getSuccessResultList(body: List<T>): Either<Failure, List<T>> =
    body.right()

//private fun <T : Any> getNoResponseResult(errorBody: ResponseBody?): Either<ErrorNetworkDto, T> =
//    errorBody.left()

private fun <T : Any> getNoResponseResult(): Either<Failure, T> =
    Failure.NoData.left()

private fun <T : Any> getNoResponseResultList(): Either<Failure, List<T>> =
    Failure.NoData.left()

private fun getFailureError() =
    Failure.ServerError.left()