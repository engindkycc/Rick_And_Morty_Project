package com.rickandmorty.rickandmortyproject.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rickandmorty.rickandmortyproject.activities.CharacterDetails
import com.rickandmorty.rickandmortyproject.activities.MainActivity
import com.rickandmorty.rickandmortyproject.databinding.MenRecyclerRowBinding
import com.rickandmorty.rickandmortyproject.databinding.UnknownRecyclerRowBinding
import com.rickandmorty.rickandmortyproject.databinding.WomenRecyclerRowBinding
import com.rickandmorty.rickandmortyproject.models.Character


class CharacterRecyclerViewAdapter(context: MainActivity) :
    RecyclerView.Adapter<ViewHolder>(){

    private var list = ArrayList<Character>()
    private val context : Context

    init{
        this.context = context
    }


    // Set the data for the adapter and
    // notify the RecyclerView of the change
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<Character>?){

        if (list != null) {
            this.list = list
            notifyDataSetChanged()

        }

    }

    class MenViewHolder(binding: MenRecyclerRowBinding) : ViewHolder(binding.root){

        val name = binding.name
        val characterImage = binding.characterImage

    }
    class WomenViewHolder(binding: WomenRecyclerRowBinding) : ViewHolder(binding.root){

        val name = binding.name
        val characterImage = binding.characterImage

    }
    class UnknownViewHolder(binding: UnknownRecyclerRowBinding) : ViewHolder(binding.root){

        val name = binding.name
        val characterImage = binding.characterImage

    }

    override fun getItemViewType(position: Int): Int {

        return when(list[position].gender) {

            "Male" -> 0
            "Female" -> 1
            else -> 2

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            0 -> {

                MenViewHolder(MenRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

            }
            1 -> {

                WomenViewHolder(WomenRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

            }
            else -> {

                UnknownViewHolder(UnknownRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

            }
        }
    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder: ViewHolder

        when (list[position].gender) {
            "Male" -> {

                viewHolder = holder as MenViewHolder
                viewHolder.name.text = list[position].name
                Glide.with(holder.itemView).load(list[position].image).into(holder.characterImage)

                holder.itemView.setOnClickListener {
                    val str : Int = list[position].id
                    val intent = Intent(holder.itemView.context, CharacterDetails::class.java)
                    intent.putExtra("character_id", str)
                    println(list[position].name)
                    holder.itemView.context.startActivity(intent)

                }

            }
            "Female" -> {

                viewHolder = holder as WomenViewHolder
                viewHolder.name.text = list[position].name
                Glide.with(holder.itemView).load(list[position].image).into(holder.characterImage)


                holder.itemView.setOnClickListener {

                    val str : Int = list[position].id
                    val intent = Intent(holder.itemView.context, CharacterDetails::class.java)
                    intent.putExtra("character_id", str)
                    println(list[position].name)
                    holder.itemView.context.startActivity(intent)

                }

            }
            else -> {

                viewHolder = holder as UnknownViewHolder
                viewHolder.name.text = list[position].name
                Glide.with(holder.itemView).load(list[position].image).into(holder.characterImage)


                holder.itemView.setOnClickListener {

                    val str : Int = list[position].id
                    val intent = Intent(holder.itemView.context, CharacterDetails::class.java)
                    intent.putExtra("character_id", str)
                    println(list[position].name)
                    holder.itemView.context.startActivity(intent)

                }

            }
        }

    }

}