package com.challenge.myapplication.network.model

import com.fasterxml.jackson.annotation.JsonProperty

data class JokeDto(
    @JsonProperty("category")
    val category: CategoryDto,
    @JsonProperty("type")
    val type: TypeDto,
    @JsonProperty("joke")
    val joke: List<String>,
    @JsonProperty("flags")
    val flags: Set<FlagDto>,
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("safe")
    val safe: Boolean,
    @JsonProperty("language")
    val lang: String,
)

enum class CategoryDto {
    @JsonProperty("Any")
    ANY,
    @JsonProperty("Programming")
    PROGRAMMING,
    @JsonProperty("Misc")
    MISC,
    @JsonProperty("Dark")
    DARK,
    @JsonProperty("Pun")
    PUN,
    @JsonProperty("Spooky")
    SPOOKY,
    @JsonProperty("Christmas")
    CHRISTMAS
}

enum class TypeDto {
    @JsonProperty("single")
    SINGLE,
    @JsonProperty("twopart")
    TWOPART,
}

data class FlagDto(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean
)
