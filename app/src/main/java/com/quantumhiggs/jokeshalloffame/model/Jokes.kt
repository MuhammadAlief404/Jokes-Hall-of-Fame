package com.quantumhiggs.jokeshalloffame.model

data class Jokes(
    val type: String,
    val value: List<Value>
)

data class Value(
    val categories: List<Any>,
    val id: Int,
    val joke: String,
    var vote: Int = 0
)