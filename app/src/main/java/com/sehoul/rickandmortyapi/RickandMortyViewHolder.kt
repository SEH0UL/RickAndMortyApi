package com.sehoul.rickandmortyapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sehoul.rickandmortyapi.databinding.ItemRickandmortyBinding

class RickandMortyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRickandmortyBinding.bind(view)

    fun bind(superheroItemResponse: RickandMortyItemResponse) {
        binding.tvRickMortyName.text = superheroItemResponse.name
    }

}
