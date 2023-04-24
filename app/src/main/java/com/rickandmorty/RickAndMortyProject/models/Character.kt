package com.rickandmorty.RickAndMortyProject.models

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val location: Location,
    val image: String,
    val created: String,
    val origin: Origin,
    val episodes: Episode
)

data class Episode(val episode: ArrayList<String>)




data class Origin(
    val name: String,
    val url: String
)


data class Location(
    val name: String,
    var url: String
)

data class CharacterDetail(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode : ArrayList<String>,
    val url : String,
    val created: String
)