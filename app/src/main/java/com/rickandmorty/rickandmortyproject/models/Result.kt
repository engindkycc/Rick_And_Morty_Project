package com.rickandmorty.rickandmortyproject.models

data class Result(
    val image: String,
    val name: String,
    val id: Int,
    val residents: ArrayList<String>
)
