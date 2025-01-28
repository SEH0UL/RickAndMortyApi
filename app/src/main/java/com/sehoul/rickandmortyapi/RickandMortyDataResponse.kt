package com.sehoul.rickandmortyapi

import com.google.gson.annotations.SerializedName

// Clase principal que representa la estructura de datos de un personaje en la API de Rick and Morty.
data class RickandMortyDataResponse(
    @SerializedName("id") val id: Int, // ID único del personaje.
    @SerializedName("name") val name: String, // Nombre del personaje.
    @SerializedName("status") val status: String, // Estado del personaje (vivo, muerto, desconocido).
    @SerializedName("species") val species: String, // Especie del personaje (humano, alienígena, etc.).
    @SerializedName("gender") val gender: String, // Género del personaje (masculino, femenino, etc.).
    @SerializedName("origin") val origin: RickandMortyOriginResponse, // Detalles del origen del personaje.
    @SerializedName("location") val location: RickandMortyLocationResponse, // Detalles de la ubicación actual del personaje.
    @SerializedName("image") val imageCharacter: String, // URL de la imagen del personaje.
    @SerializedName("url") val url: String, // URL del recurso del personaje en la API.
    @SerializedName("episode") val episode: List<String> // Lista de episodios en los que aparece el personaje.
)

// Clase que representa los detalles del origen de un personaje.
data class RickandMortyOriginResponse(
    @SerializedName("name") val name: String, // Nombre del lugar de origen.
    @SerializedName("url") val url: String? // URL del recurso de origen en la API (puede ser nulo).
)

// Clase que representa los detalles de la ubicación actual de un personaje.
data class RickandMortyLocationResponse(
    @SerializedName("name") val name: String, // Nombre del lugar de ubicación actual.
    @SerializedName("url") val url: String // URL del recurso de ubicación en la API.
)
