package com.almagro.presentation

import arrow.core.*
import kotlin.coroutines.CoroutineContext
import arrow.core.extensions.either.applicativeError.handleError
import kotlinx.coroutines.*

interface WithScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
    val io: CoroutineContext

    fun <A, B> CoroutineScope.launchIOSafe(
        f: suspend () -> Either<A, B>,
        error: (A) -> Unit = {},
        success: suspend (B) -> Unit = {}
    ): Job =
        launch {
            IO { f() }.suspendMap { Main { success(it) } }.handleError { error(it) }
        }

    suspend fun <T> IO(block: suspend CoroutineScope.() -> T): T =
        withContext(io) { block() }

    suspend fun <T> Main(block: suspend CoroutineScope.() -> T): T =
        withContext(coroutineContext) { block() }

    fun cancel(): Unit =
        coroutineContext.cancelChildren()

    suspend fun <A, B, C> EitherOf<A, B>.suspendMap(f: suspend (B) -> C): Either<A, C> =
        flatMapEffect { Right(f(it)) }

    suspend fun <A, B, C> EitherOf<A, B>.flatMapEffect(f: suspend (B) -> Either<A, C>): Either<A, C> =
        fix().fold({ it.left() }, { f(it) })
}