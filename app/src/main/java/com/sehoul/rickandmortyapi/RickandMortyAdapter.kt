package com.sehoul.rickandmortyapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Adaptador personalizado para gestionar y mostrar datos de la API de Rick and Morty en un RecyclerView
class RickandMortyAdapter(private var RickandMortyList: List<RickandMortyDataResponse> = emptyList()) :
    RecyclerView.Adapter<RickandMortyViewHolder>() {

    // Método para actualizar la lista de datos mostrada en el RecyclerView
    fun updateList(list: List<RickandMortyDataResponse>) {
        RickandMortyList = list // Se actualiza la lista interna con los nuevos datos
        notifyDataSetChanged() // Se notifica al adaptador que los datos han cambiado, para refrescar la vista
    }

    // Crea nuevos ViewHolder inflando la vista de diseño definida para cada elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickandMortyViewHolder {
        return RickandMortyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rickandmorty, parent, false) // Inflamos el layout del elemento
        )
    }

    // Vincula los datos a cada ViewHolder según la posición en la lista
    override fun onBindViewHolder(holder: RickandMortyViewHolder, position: Int) {
        holder.bind(RickandMortyList[position]) // Llama al método "bind" para asignar los datos al ViewHolder
    }

    // Devuelve el tamaño actual de la lista de datos
    override fun getItemCount() = RickandMortyList.size
}
