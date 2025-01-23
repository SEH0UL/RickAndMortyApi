package com.sehoul.rickandmortyapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sehoul.rickandmortyapi.databinding.ItemRickandmortyBinding
import com.squareup.picasso.Picasso

class RickandMortyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRickandmortyBinding.bind(view)

    fun bind(character: RickandMortyDataResponse) {
        binding.tvRickMortyName.text = character.name
        binding.tvRickMortyStatus.text = "Estado: ${character.status}"
        binding.tvRickMortySpecies.text = "Especie: ${character.species}"
        binding.tvRickMortyGender.text = "Género: ${character.gender}"
        binding.tvRickMortyOrigin.text = "Origen: ${character.origin.name}"
        binding.tvRickMortyLocation.text = "Ubicación: ${character.location.name}"

        // Mostrar cantidad de episodios
        binding.tvRickMortyEpisodes.text = "Aparece en: ${character.episode.size} Episodios"
        Picasso.get().load(character.imageCharacter).into(binding.ivCharacter)
    }
}



