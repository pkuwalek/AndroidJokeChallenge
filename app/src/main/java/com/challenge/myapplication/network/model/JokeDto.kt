package com.challenge.myapplication.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JokeDto(
    val category: CategoryDto,
    val type: TypeDto,

    // SINGLE joke → present only for single
    val joke: String? = null,

    // TWOPART joke → present only for twopart
    val setup: String? = null,
    val delivery: String? = null,

    val flags: FlagDto,
    val id: Int,
    val safe: Boolean,
    @SerialName("lang")
    val lang: String
)

@Serializable
enum class CategoryDto {
    @SerialName("Any")
    ANY,
    @SerialName("Programming")
    PROGRAMMING,
    @SerialName("Misc")
    MISC,
    @SerialName("Dark")
    DARK,
    @SerialName("Pun")
    PUN,
    @SerialName("Spooky")
    SPOOKY,
    @SerialName("Christmas")
    CHRISTMAS,
}

@Serializable
enum class TypeDto {
    @SerialName("single")
    SINGLE,
    @SerialName("twopart")
    TWOPART,
}

@Serializable
data class FlagDto(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean,
)
