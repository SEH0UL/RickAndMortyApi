package com.sehoul.rickandmortyapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sehoul.rickandmortyapi.databinding.ItemRickandmortyBinding
import com.squareup.picasso.Picasso

// ViewHolder para manejar y mostrar la información de un personaje de Rick and Morty en el RecyclerView
class RickandMortyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Binding para acceder a los elementos de la vista definida en el layout 'item_rickandmorty.xml'
    private val binding = ItemRickandmortyBinding.bind(view)

    // Método para enlazar los datos de un personaje a los elementos visuales del ViewHolder
    fun bind(character: RickandMortyDataResponse) {
        // Asignación del nombre del personaje al TextView correspondiente
        binding.tvRickMortyName.text = character.name
        // Asignación del estado del personaje al TextView correspondiente
        binding.tvRickMortyStatus.text = "Estado: ${character.status}"
        // Asignación de la especie del personaje al TextView correspondiente
        binding.tvRickMortySpecies.text = "Especie: ${character.species}"
        // Asignación del género del personaje al TextView correspondiente
        binding.tvRickMortyGender.text = "Género: ${character.gender}"
        // Asignación del origen del personaje al TextView correspondiente
        binding.tvRickMortyOrigin.text = "Origen: ${character.origin.name}"
        // Asignación de la ubicación actual del personaje al TextView correspondiente
        binding.tvRickMortyLocation.text = "Ubicación: ${character.location.name}"

        // Mostrar la cantidad de episodios en los que aparece el personaje
        binding.tvRickMortyEpisodes.text = "Aparece en: ${character.episode.size} Episodios"
        // Carga de la imagen del personaje utilizando la biblioteca Picasso
        Picasso.get().load(character.imageCharacter).into(binding.ivCharacter)
    }
}