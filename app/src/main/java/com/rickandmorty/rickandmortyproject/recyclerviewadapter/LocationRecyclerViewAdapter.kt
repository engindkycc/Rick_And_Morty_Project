package com.rickandmorty.rickandmortyproject.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rickandmorty.rickandmortyproject.activities.MainActivity
import com.rickandmorty.rickandmortyproject.api.OnClickInterface
import com.rickandmorty.rickandmortyproject.R
import com.rickandmorty.rickandmortyproject.databinding.LocationRecyclerRowBinding
import com.rickandmorty.rickandmortyproject.models.Result



@Suppress("DEPRECATION", "PropertyName")
class LocationRecyclerViewAdapter(context: MainActivity, onClickHorizontal: OnClickInterface) :
    RecyclerView.Adapter<LocationRecyclerViewAdapter.HorizontalViewHolder>() {
    var single_selection_position = -1


    // List of characters to display
    private var list = ArrayList<Result>()
    private val context: Context
    private val onClick: OnClickInterface

    init {
        this.context = context
        this.onClick = onClickHorizontal
    }


    // Set the data for the adapter and
    // notify the RecyclerView of the change
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Result>) {
        this.list = list as ArrayList<Result>
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list: List<Result>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    inner class HorizontalViewHolder(binding: LocationRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val name = binding.textView

        init {

            binding.rowItem.setOnClickListener { setSingleSelection(adapterPosition) }
        }

        private fun setSingleSelection(adapterPosition: Int) {
            if (adapterPosition == RecyclerView.NO_POSITION) return
            notifyItemChanged(single_selection_position)
            single_selection_position = adapterPosition
            notifyItemChanged(single_selection_position)
            onClick.onClick(list[adapterPosition])


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        return HorizontalViewHolder(
            LocationRecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {

        // loading character name in text View
        holder.name.text = list[position].name

        if (single_selection_position == position) {

            holder.itemView.setBackgroundColor(context.resources.getColor(R.color.grey))
        } else {

            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }


    }


}