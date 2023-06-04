package com.rickandmorty.rickandmortyproject.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rickandmorty.rickandmortyproject.R
import com.rickandmorty.rickandmortyproject.api.OnClickInterface
import com.rickandmorty.rickandmortyproject.api.RetrofitInstance
import com.rickandmorty.rickandmortyproject.databinding.ActivityMainBinding
import com.rickandmorty.rickandmortyproject.models.Character
import com.rickandmorty.rickandmortyproject.models.LocationResponse
import com.rickandmorty.rickandmortyproject.models.Result


import com.rickandmorty.rickandmortyproject.recyclerviewadapter.LocationRecyclerViewAdapter
import com.rickandmorty.rickandmortyproject.recyclerviewadapter.CharacterRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnClickInterface {

    private lateinit var recyclerView: RecyclerView
    private lateinit var horizantalRecyclerView: RecyclerView
    private lateinit var horizontalAdapter: LocationRecyclerViewAdapter
    private lateinit var verticalAdapter: CharacterRecyclerViewAdapter
    private var isCompleted: Boolean = false
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Horizontal RecyclerView
        horizantalRecyclerView = findViewById(R.id.horizontal_recyclerView)
        horizontalAdapter = LocationRecyclerViewAdapter(this, this)
        horizantalRecyclerView.itemAnimator = DefaultItemAnimator()
        horizantalRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
        horizantalRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        horizantalRecyclerView.adapter = horizontalAdapter

        //Vertical RecyclerView
        recyclerView = findViewById(R.id.vertical_recyclerView)
        verticalAdapter = CharacterRecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = verticalAdapter


        var currentPage = 1
        var isLoading = false

        horizantalRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition =
        layoutManager.findFirstCompletelyVisibleItemPosition()


        if (!isCompleted && !isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {

                    isLoading = true
                    currentPage++
                    RetrofitInstance.api.getLocations(currentPage)
                        .enqueue(object : Callback<LocationResponse> {
                            override fun onResponse(
                                call: Call<LocationResponse>,
                                response: Response<LocationResponse>
                            ) {

                                if (response.isSuccessful) {

                                    val locations = response.body()?.results ?: emptyList()
                                    horizontalAdapter.addAll(locations)
                                    isLoading = false

                                }

                                if (response.code() == 404) {

                                    isCompleted = true

                                }

                            }

                            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {

                                isLoading = false
                                // Hata durumunda bir şeyler yapabilirsiniz.

                            }

                        })

                }

            }

        })







        RetrofitInstance.api.getLocations(currentPage).enqueue(object : Callback<LocationResponse> {
            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                if (response.body() != null) {
                    horizontalAdapter.setData(response.body()!!.results)
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }


        })


    }


    override fun onClick(location: Result) {
        if (location.residents.isEmpty()) {
            val emptyList: ArrayList<Character> = arrayListOf()
            verticalAdapter.setData(emptyList)
            return
        }

        var idList = ""
        for (resident in location.residents) {
            idList += resident.split("/").last() + ","
        }
        idList = idList.substring(0, idList.lastIndex)
        idList = "[$idList]"
        RetrofitInstance.api.getCharacters(idList).enqueue(object : Callback<ArrayList<Character>> {
            override fun onResponse(
                call: Call<ArrayList<Character>>,
                response: Response<ArrayList<Character>>
            ) {
                if (response.body() != null) {
                    verticalAdapter.setData(response.body())
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<ArrayList<Character>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }

        })

    }


}