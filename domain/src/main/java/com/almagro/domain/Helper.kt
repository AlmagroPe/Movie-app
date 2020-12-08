package com.almagro.domain

import arrow.core.*

fun <A> A.toOption(f: (A) -> Boolean): Option<A> =
    if (f(this)) this.some() else None

fun <A> Option<A>.error(f: () -> Unit) {
    fold({ f() }, {})
}

fun <A, B, C> Either<A, B>.error(f: () -> Either<C, B>): Either<C, B> =
    fold({ f() }, { it.right() })

suspend fun <A, C> Option<A>.suspendMap(f: suspend (A) -> C): Option<C> =
    when (this) {
        is Some -> Some(f(this.t))
        is None -> None
    }

suspend fun <A, B, C> EitherOf<A, B>.suspendMap(f: suspend (B) -> C): Either<A, C> =
    flatMapEffect { Right(f(it)) }

suspend fun <B> EitherOf<*, B>.getOrElseEffect(default: suspend () -> B): B =
    fix().fold({ default() }, ::identity)

suspend fun <A, B, C> EitherOf<A, B>.flatMapEffect(f: suspend (B) -> Either<A, C>): Either<A, C> =
    fix().fold({ it.left() }, { f(it) })

suspend fun <A, B> EitherOf<A, B>.handleErrorWithEffect(f: suspend (A) -> EitherOf<A, B>): Either<A, B> =
    fix().fold({ f(it).fix() }, { it.right() })