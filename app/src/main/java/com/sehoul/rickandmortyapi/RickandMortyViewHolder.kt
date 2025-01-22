package com.sehoul.rickandmortyapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sehoul.rickandmortyapi.databinding.ItemRickandmortyBinding
import com.squareup.picasso.Picasso

class RickandMortyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRickandmortyBinding.bind(view)

    fun bind(character: RickandMortyDataResponse) {
        binding.tvRickMortyName.text = character.name
        binding.tvRickMortyStatus.text = "Status: ${character.status} | Species: ${character.species}"
        Picasso.get().load(character.imageCharacter).into(binding.ivCharacter)
    }
}

