package com.rickandmorty.rickandmortyproject.api

import com.rickandmorty.rickandmortyproject.models.Character
import com.rickandmorty.rickandmortyproject.models.CharacterDetail
import com.rickandmorty.rickandmortyproject.models.LocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickApi  {

    @GET("character/{idList}")
    fun getCharacters(@Path("idList") idList: String) : Call<ArrayList<Character>>

    @GET("character/{id}")
    fun getCharacterDetail(@Path("id") id: Int): Call<CharacterDetail>

    @GET("location")
    fun getLocations(@Query("page") page: Int): Call<LocationResponse>

}






