package com.sehoul.rickandmortyapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RickandMortyAdapter(private var RickandMortyList: List<RickandMortyDataResponse> = emptyList()) :
    RecyclerView.Adapter<RickandMortyViewHolder>() {

    fun updateList(list: List<RickandMortyDataResponse>) {
        RickandMortyList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickandMortyViewHolder {
        return RickandMortyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rickandmorty, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RickandMortyViewHolder, position: Int) {
        holder.bind(RickandMortyList[position])
    }

    override fun getItemCount() = RickandMortyList.size
}
