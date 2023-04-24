package com.rickandmorty.RickAndMortyProject.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rickandmorty.RickAndMortyProject.api.RetrofitInstance
import com.rickandmorty.RickAndMortyProject.databinding.ActivityCharacterDetailsBinding
import com.rickandmorty.RickAndMortyProject.models.CharacterDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetails : AppCompatActivity() {
        private lateinit var binding : ActivityCharacterDetailsBinding

        @SuppressLint("SuspiciousIndentation")
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val characterId = intent.getIntExtra("character_id", -1)

                RetrofitInstance.api.getCharacterDetail(characterId).enqueue(object :
                        Callback<CharacterDetail> {
                        override fun onResponse(call: Call<CharacterDetail>, response: Response<CharacterDetail>) {
                                if (response.isSuccessful) {

                                        val character = response.body()

                                        if (character != null) {


                                                val episodeUrls = character.episode
                                                var ids: String = ""

                                                for (url in episodeUrls){

                                                        //val emptyMutableList = ArrayList<String>()
                                                        val episodeId = (url.split("/"))[5]
                                                        ids += episodeId
                                                        if(episodeUrls.last() != url){
                                                                ids += ", ";

                                                        }

                                                }

                                                binding.getcharacterEpisodes?.text = ids
                                                binding.characterName?.text = character.name
                                                binding.getcharacterStatus?.text = character.status
                                                binding.getcharacterSpecy?.text = character.species
                                                binding.getcharacterGender?.text = character.gender
                                                binding.getcharacterOrigin?.text = character.origin.name
                                                binding.getcharacterLocation?.text = character.location.name
                                                binding.getcharacterCreated?.text = character.created
                                                Glide.with(this@CharacterDetails).load(character.image).into(binding.characterImage)

                                        }

                                }

                        }

                        override fun onFailure(call: Call<CharacterDetail>, t: Throwable) {

                                Log.e("API Error", "Error getting character details", t)
                                Toast.makeText(this@CharacterDetails , "Error getting character details", Toast.LENGTH_SHORT).show()

                        }

                })

        }




    }
