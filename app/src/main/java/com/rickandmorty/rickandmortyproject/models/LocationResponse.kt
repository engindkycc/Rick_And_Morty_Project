package com.rickandmorty.rickandmortyproject.models

data class LocationResponse(
    val info: Info,
    val results: List<Result>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)