package com.rickandmorty.RickAndMortyProject.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Property that lazily initializes
    // the RickApi implementation
    val api : RickApi by lazy {
        // Build the Retrofit instance with
        // a base URL and a Gson converter factory
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickApi::class.java)
    }
}