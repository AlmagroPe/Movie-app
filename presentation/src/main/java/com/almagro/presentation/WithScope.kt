package com.almagro.presentation

import kotlin.coroutines.CoroutineContext
import arrow.core.Either
import arrow.core.extensions.either.applicativeError.handleError
import arrow.core.extensions.either.applicativeError.handleErrorWith
import com.almagro.domain.flatMapEffect
import com.almagro.domain.suspendMap
import kotlinx.coroutines.*

interface WithScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
    val io: CoroutineContext

    //TODO: clean this file
    fun <A, B> CoroutineScope.launchIOSafe(
        f: suspend () -> Either<A, B>,
        error: (A) -> Unit = {},
        success: suspend (B) -> Unit = {}
    ): Job =
        launch {
            IO { f() }.suspendMap { Main { success(it) } }.handleError { error(it) }
        }

    fun <A> CoroutineScope.launchIO(
        f: suspend () -> A,
        success: (A) -> Unit = {}
    ): Job =
        launch {
            success(IO { f() })
        }

    fun <A> launchIO(
        f: suspend () -> A
    ): Job =
        launch { IO { f() } }

    suspend fun <T> IO(block: suspend CoroutineScope.() -> T): T =
        withContext(io) { block() }

    suspend fun <T> AsynckIO(block: suspend CoroutineScope.() -> T): Deferred<T> =
        async(io) { block() }

    suspend fun <T> Main(block: suspend CoroutineScope.() -> T): T =
        withContext(coroutineContext) { block() }

    fun <A, B> Either<A, B>.error(f: (A) -> Unit): Either<A, B> =
        handleErrorWith { f(it); this }

    suspend fun <A, B> Either<A, B>.success(f: (B) -> Unit): Either<A, B> =
        flatMapEffect { f(it); this }

    fun <A> launchMain(
        f: suspend () -> A
    ): Job =
        launch { f() }

    fun cancel(): Unit =
        coroutineContext.cancelChildren()
}