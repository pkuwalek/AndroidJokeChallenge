package com.challenge.myapplication.common

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    fun isLeft(): Boolean = this is Left
    fun isRight(): Boolean = this is Right

    inline fun <T> fold(ifLeft: (L) -> T, ifRight: (R) -> T): T = when (this) {
        is Left -> ifLeft(value)
        is Right -> ifRight(value)
    }

    inline fun <T> map(transform: (R) -> T): Either<L, T> = when (this) {
        is Left -> this
        is Right -> Right(transform(value))
    }

    inline fun ifLeft(f: (L) -> Unit): Either<L, R> =
        fold({ f(it); Left(it) }, { Right(it) })

    inline fun ifRight(f: (R) -> Unit): Either<L, R> =
        fold({ Left(it) }, { f(it); Right(it) })
}

inline fun <T> Either<Throwable, T>.getOrThrow(): T = when (this) {
    is Either.Left -> throw value
    is Either.Right -> value
}