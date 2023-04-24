package com.rickandmorty.RickAndMortyProject.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rickandmorty.RickAndMortyProject.activities.CharacterDetails
import com.rickandmorty.RickAndMortyProject.activities.MainActivity
import com.rickandmorty.RickAndMortyProject.databinding.MenRecyclerRowBinding
import com.rickandmorty.RickAndMortyProject.databinding.UnknownRecyclerRowBinding
import com.rickandmorty.RickAndMortyProject.databinding.WomenRecyclerRowBinding
import com.rickandmorty.RickAndMortyProject.models.Character


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

    class MenViewHolder(binding: MenRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

        val name = binding.name
        val characterImage = binding.characterImage

    }
    class WomenViewHolder(binding: WomenRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

        val name = binding.name
        val characterImage = binding.characterImage

    }
    class UnknownViewHolder(binding: UnknownRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

        val name = binding.name
        val characterImage = binding.characterImage

    }

    override fun getItemViewType(position: Int): Int {

        when(list[position].gender) {

            "Male" -> return 0;
            "Female" -> return 1;
            else -> return 2;

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == 0) {

            return MenViewHolder(MenRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        } else if (viewType == 1) {

            return WomenViewHolder(WomenRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        } else {

            return UnknownViewHolder(UnknownRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        }
    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder: ViewHolder

        if (list[position].gender == "Male") {

            viewHolder = holder as MenViewHolder;
            viewHolder.name.text = list[position].name
            Glide.with(holder.itemView).load(list[position].image).into(holder.characterImage)

            holder.itemView.setOnClickListener {
                val str : Int = list[position].id
                val intent = Intent(holder.itemView.context, CharacterDetails::class.java)
                intent.putExtra("character_id", str)
                println(list[position].name)
                holder.itemView.context.startActivity(intent)

            }

        }else if (list[position].gender == "Female") {

            viewHolder = holder as WomenViewHolder;
            viewHolder.name.text = list[position].name
            Glide.with(holder.itemView).load(list[position].image).into(holder.characterImage)


            holder.itemView.setOnClickListener {

                val str : Int = list[position].id
                val intent = Intent(holder.itemView.context, CharacterDetails::class.java)
                intent.putExtra("character_id", str)
                println(list[position].name)
                holder.itemView.context.startActivity(intent)

            }

        } else {

            viewHolder = holder as UnknownViewHolder;
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