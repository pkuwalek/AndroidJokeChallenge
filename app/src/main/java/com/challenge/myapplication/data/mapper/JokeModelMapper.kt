package com.challenge.myapplication.data.mapper

import com.challenge.myapplication.data.model.JokeCategory
import com.challenge.myapplication.data.model.JokeModel
import com.challenge.myapplication.data.model.JokeType
import com.challenge.myapplication.network.model.CategoryDto
import com.challenge.myapplication.network.model.JokeDto
import com.challenge.myapplication.network.model.TypeDto

fun JokeDto.toDomain() = JokeModel(
    id = this.id,
    type = this.type.toDomain(),
    category = this.category.toDomain(),
    joke = this.joke,
    setup = this.setup,
    punchline = this.delivery,
)

private fun CategoryDto.toDomain(): JokeCategory =
    when (this) {
        CategoryDto.ANY -> JokeCategory.ANY
        CategoryDto.MISC -> JokeCategory.MISC
        CategoryDto.PUN -> JokeCategory.PUN
        CategoryDto.DARK -> JokeCategory.DARK
        CategoryDto.SPOOKY -> JokeCategory.SPOOKY
        CategoryDto.CHRISTMAS -> JokeCategory.CHRISTMAS
        CategoryDto.PROGRAMMING -> JokeCategory.PROGRAMMING
    }

private fun TypeDto.toDomain(): JokeType =
    when (this) {
        TypeDto.SINGLE -> JokeType.SINGLE
        TypeDto.TWOPART -> JokeType.TWOPART
    }

